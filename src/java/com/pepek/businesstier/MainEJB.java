/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businesstier;

import com.pepek.enitities.Users;
import com.pepek.integrationTier.FlatstableFacade;
import com.pepek.integrationTier.UsersFacade;
import com.pepek.misc.Utilieties;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

/**
 *
 * @author Michal
 */
@Stateless
public class MainEJB {

    @EJB
    private UsersFacade usersFacade;

    @EJB
    private FlatstableFacade flatstableFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    Users GetUser(String username) {
        List<Users> usersList = usersFacade.findAll();
        for (Users user : usersList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public boolean LoginAuthentication(String username, String password) {
        Users user = GetUser(username);
        byte[] encryptedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encryptedPassword = digest.digest(password.concat(user.getSalt()).getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsersFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (encryptedPassword.equals(user.getPassword().getBytes()) == true) {
            return true;
        }

        return false;
    }

    public void AddUserToDB(String user, String password, String email, int telefon, Date date,
            Utilieties.Sex plec, String adres, Part awatar) throws NoSuchAlgorithmException {
        String salt = CreateNewSalt();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encryptedPassword = digest.digest(password.concat(salt).getBytes());

        usersFacade.create(new Users(usersFacade.count() + 1, user, Arrays.toString(encryptedPassword), adres, email, telefon, plec.name(),
                date, (Serializable) awatar, CreateNewSalt()));

    }

    protected String CreateNewSalt() {
        SecureRandom random = new SecureRandom();
        random.generateSeed(32);
        System.out.println("randomSalt: " + random);
        return random.toString();
    }

}
