package com.pepek.integrationTier.facades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pepek.integrationTier.enitities.Flatstable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michal
 */
@Stateless
public class FlatstableFacade extends AbstractFacade<Flatstable> {

    @PersistenceContext(unitName = "OtoNaszDomgitPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlatstableFacade() {
        super(Flatstable.class);
    }
    
}
