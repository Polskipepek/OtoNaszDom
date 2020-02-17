/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.jsf.beans;

import com.pepek.misc.Utilieties;
import java.awt.Image;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Michal
 */
@ManagedBean(name = "userController")
@RequestScoped
public class UserController {

    FacesContext context = FacesContext.getCurrentInstance();

    public boolean ValidateUserRegisterCredentials() {
        return false;
    }

    public boolean ValidateUserLoginCredentials(String username, String password) {
        //System.out.println(username + " " + password);
        context.getApplication().evaluateExpressionGet(context, "#{userController}", Object.class);
        return false;
    }

}
