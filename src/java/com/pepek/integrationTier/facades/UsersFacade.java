/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.integrationTier.facades;

import com.pepek.integrationTier.enitities.Users;
import com.pepek.misc.Utilieties;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michal
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "OtoNaszDomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    Users GetUser(String username) {
        List<Users> usersList = findAll();
        for (Users user : usersList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public String LoginAuthentication(String username, String password) {
        Users user = null;
        user = GetUser(username);
        if (user == null) {
            return "notExist";
        }
        String salt = user.getSalt();
        String concatedPassword = password.concat(salt);
        byte[] hashed = HashSha256(concatedPassword);

        String encryptedPassword = bytesToString(hashed);

        System.out.println("haslo:" + password + ", salt:" + salt + ", sha: " + encryptedPassword+" == " + user.getPassword());

        return encryptedPassword.equals(user.getPassword()) == true ? "true" : "false";
    }

    protected byte[] HashSha256(String string) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return digest.digest(string.getBytes());

    }

    protected String bytesToString(byte[] bytes) {
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String string = encoder.encodeToString(bytes);
        return string;
    }

    protected String GenerateNewSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String salt = bytesToString(bytes);
        System.out.println("randomSalt: " + salt);
        return salt;
    }

    public void AddUserToDB(String username, String password, String email, int telefon, Date date, Utilieties.Sex plec) {
        String salt = GenerateNewSalt();
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bencryptedPassword = digest.digest(password.concat(salt).getBytes());
        String sencryptedPassword = bytesToString(bencryptedPassword);
        Users user = new Users(count() + 1, username, sencryptedPassword, email, telefon, plec.name(), date, salt);
        create(user);

    }

    public UsersFacade() {
        super(Users.class);
    }

}
