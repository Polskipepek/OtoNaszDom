/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.EJBs;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import com.pepek.integrationTier.facades.FlatstableFacade;
import com.pepek.integrationTier.facades.UsersFacade;
import com.pepek.misc.Utilieties;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

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

    public void AddUserToDB(String user, String password, String email, int telefon, Date date, Utilieties.Sex plec, String adres, File awatar) throws NoSuchAlgorithmException {
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

    public Flatstable AddNewFlatToDB(Users owner, String name, String description, float price, File fileImage) throws IOException {
        //byte[] temp = Files.readAllBytes(IOUtils.toByteArray(fileImage.toPath()));
        byte[] temp = convertFileContentToBlob(fileImage.getPath());

        Flatstable mieszkanko = new Flatstable(flatstableFacade.count() + 1, name, description, temp);
        flatstableFacade.create(mieszkanko);

        return null;
    }

    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. "
                    + e.getMessage());
        }
        return fileContent;
    }

    public List<Flatstable> GetFlats(String query, Users owner) {
        List<Flatstable> flatsRoot = flatstableFacade.findAll();
        List<Flatstable> flatsInfo = new ArrayList<>();

        if (owner == null) {
            if (query == null || query.equals("")) {
                flatsInfo = flatstableFacade.findAll();
            } else if (query.length() > 0) {
                for (Flatstable flat : flatsRoot) {
                    if (flat.getName().contains(query)) {
                        flatsInfo.add(flat);
                    }
                }
            }
        } else if (owner != null) {
            if (query.length() > 0) {
                for (Flatstable flat : flatsRoot) {
                    if (Objects.equals(owner.getId(), flat.getId()) && (flat.getName().contains(query) || flat.getDescription().contains(query))) {
                        flatsInfo.add(flat);
                    }
                }
            } else if (query == null || query.length() < 1) {
                for (Flatstable flat : flatsRoot) {
                    if (Objects.equals(owner.getId(), flat.getId())) {
                        flatsInfo.add(flat);
                    }
                }
            }
        }
        return flatsInfo;
    }

    public List<Users> GetUsersWithFlats() {
        List<Users> usersRoot = usersFacade.findAll();
        List<Users> users = new ArrayList<>();
        for (Users user : usersRoot) {
            if (user.getUserflats().size() > 0) {
                users.add(user);
            }
        }
        return users;
    }

}
