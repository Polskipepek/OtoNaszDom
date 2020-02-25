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

import static com.pepek.businessTier.EJBs.MainEJB.convertFileContentToBlob;
import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public Flatstable AddNewFlatToDB(Users owner, String name, String description, float price, File fileImage) throws IOException {
        //byte[] temp = Files.readAllBytes(IOUtils.toByteArray(fileImage.toPath()));
        byte[] temp = convertFileContentToBlob(fileImage.getPath());

        Flatstable mieszkanko = new Flatstable(count() + 1, name, description, temp);
        create(mieszkanko);

        return null;
    }

    public List<Flatstable> GetFlats(String query, Users owner) {
        List<Flatstable> flatsRoot = findAll();
        List<Flatstable> flatsInfo = new ArrayList<>();

        if (owner == null) {
            if (query == null || query.equals("")) {
                flatsInfo = findAll();
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
                    if (flat.compareWithQuery(query, owner)) {
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
}
