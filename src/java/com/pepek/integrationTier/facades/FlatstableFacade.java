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
package com.pepek.integrationTier.facades;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

/**
 *
 * @author Michal
 */
@Stateless
public class FlatstableFacade extends AbstractFacade<Flatstable> {

    @PersistenceContext(unitName = "OtoNaszDomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlatstableFacade() {
        super(Flatstable.class);
    }

    public Flatstable AddNewFlatToDB(Users owner, String name, String description, float price, List<String> imagesPath, float size) {
        String temp = "";

        if (imagesPath != null && imagesPath.size() > 0) {
            for (String s : imagesPath) {
                temp += s + "\n";
            }
        }

        Flatstable mieszkanko = new Flatstable(count() + 1, name, description, size, price, temp, owner);
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

    public List<String> upload(Part[] files) {
        List<String> paths = new ArrayList<>();
        for (Part file : files) {
            try {
                InputStream in = file.getInputStream();

                File f = new File("A:\\Users\\Michal\\Documents\\NetBeansProjects\\Git\\OtoNaszDom\\HOST" + file.getSubmittedFileName());
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[8192];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                paths.add(f.getAbsolutePath());
                out.close();
                in.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

        }
        return paths;
    }
}
