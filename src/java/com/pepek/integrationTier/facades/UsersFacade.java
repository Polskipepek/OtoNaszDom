/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.integrationTier.facades;

import com.pepek.integrationTier.enitities.Users;
import com.pepek.misc.SessionUtils;
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
import javax.annotation.PreDestroy;
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

    @PreDestroy
    public void destruct() {
        em.close();
    }

    public Users GetUser(String username) {
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
        byte[] hashed = HashSha256(password.concat(salt));
        String encryptedPassword = bytesToString(hashed);

        System.out.println("haslo:" + password + ", salt:" + salt + ", sha: " + encryptedPassword + " == " + user.getPassword());

        return encryptedPassword.equals(user.getPassword()) == true ? "true" : "false";
    }

    public String AddUserToDB(String username, String password, String email, int telefon, Date date, Utilieties.Sex plec) {

        for (Users user : findAll()) {
            if (user.getUsername().equals(username)) {
                return "UserExist";
            }
            if (user.getNumber().equals(telefon)) {
                return "NumberExist";
            }
            if (user.getEmail().equals(email)) {
                return "emailExist";
            }
        }

        if (!email.contains("@")) {
            return "@inMail";
        }

        String salt = GenerateNewSalt();
        byte[] b_encryptedPassword = HashSha256(password.concat(salt));
        String s_encryptedPassword = bytesToString(b_encryptedPassword);

        Users user = new Users(count() + 1, username, s_encryptedPassword, email, telefon, plec.name(), date, salt);
        create(user);
        if (SessionUtils.Errors.isEmpty()) {
            return "true";
        } else {
            return SessionUtils.Errors.get(0);
        }
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

    public UsersFacade() {
        super(Users.class);
    }

    public Users InitProfile() {
        Users user = GetUser(SessionUtils.getUserName());
        user.setUsername(user.getUsername());
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setAddress(user.getAddress());
        user.setNumber(user.getNumber());
        user.setSex(Utilieties.Sex.valueOf(user.getSex()).toString());
        user.setDate(new Date(user.getDate().getTime()));
        return user;
    }
}
