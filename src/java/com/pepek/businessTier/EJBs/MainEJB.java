/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.EJBs;

import com.pepek.businessTier.facades.FlatstableFacade;
import com.pepek.businessTier.facades.UsersFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
}
