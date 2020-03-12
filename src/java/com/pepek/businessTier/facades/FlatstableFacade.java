/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.facades;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import com.pepek.internetTier.beans.BrowserController;
import com.pepek.misc.SessionUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Michal
 */
@Stateless
public class FlatstableFacade extends AbstractFacade<Flatstable> {
    //public final String imagesRootPath= System.getProperty("user.dir");
    public final String imagesRootPath = "A:\\Users\\Michal\\Programowanie\\NetBeansProjects\\OtoNaszDom" + "\\web\\resources\\images\\";
    
    @PersistenceContext(unitName = "OtoNaszDomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private UsersFacade usersFacade;
    
    
    public FlatstableFacade() {
        super(Flatstable.class);
    }

    public Flatstable AddFlatToDB(Part images, String name, String desc, float price, float size) {
        List<String> imagesNames = new ArrayList<>();
        Random random = new Random();
        try {
            for (Part part : getAllParts(images)) {
                String fileName = count() + 1 + "_" + random.nextInt(1000000000);
                String extension = FilenameUtils.getExtension(part.getSubmittedFileName());

                InputStream fileContent = part.getInputStream();
                File file = new File(imagesRootPath + fileName + "." + extension);
                FileUtils.copyInputStreamToFile(fileContent, file);

                System.out.println("Uploaded file successfully saved in " + file.getAbsolutePath());
                imagesNames.add(fileName + "." + extension);

            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //List<String> imagesPath = flatstableFacade.upload(files);
        Users owner =usersFacade.GetUser(SessionUtils.getUserName());
        return AddNewFlatToDB(owner, name, desc, price, imagesNames, size);

    }

    public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Collection<Part> parts = new ArrayList<>();
        Collection<Part> col = request.getParts();

        for (Part p : col) {
            if (p.getName().equals(part.getName())) {
                parts.add(p);
            }
        }
        return parts;
    }

    Flatstable AddNewFlatToDB(Users owner, String name, String description, float price, List<String> imagesPath, float size) {
        String imagesNamesInString = "";

        if (imagesPath != null && imagesPath.size() > 0) {
            for (String s : imagesPath) {
                imagesNamesInString += s + "\n";
            }
        }

        Flatstable mieszkanko = new Flatstable(count() + 1, name, description, size, price, imagesNamesInString, owner);
        create(mieszkanko);

        return mieszkanko;
    }

    public List<Flatstable> GetFlats(String query, Users owner) {
        List<Flatstable> flatsRoot = findAll();
        List<Flatstable> flatsInfo = new ArrayList<>();

        if (owner == null) {
            for (Flatstable flat : flatsRoot) {
                if (flat.getName().contains(query)) {
                    flatsInfo.add(flat);
                }
            }
        } else if (owner != null) {
            if (query.length() > 0) {
                for (Flatstable flat : flatsRoot) {
                    if (flat.compareWithQuery(query, owner)) {
                        flatsInfo.add(flat);
                    }
                }
            } else if (query.length() < 1) {
                for (Flatstable flat : flatsRoot) {
                    if (Objects.equals(owner.getId(), flat.getId())) {
                        flatsInfo.add(flat);
                    }
                }
            }
        }

        return flatsInfo;
    }

    public List<Flatstable> GetAllFlats() {
        return findAll();
    }
    public Flatstable getFlat(Integer id) {
        for (Flatstable f : GetAllFlats()) {
            if (f.getId().intValue() == id) {
                return f;
            }
        }
        return null;
    }
    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

}
