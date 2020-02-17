/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.beans;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Michal
 */
@ManagedBean
@ApplicationScoped
public class ViewerHelper implements Serializable {

    private String currentForm = "userLogin.xhtml";
    private boolean toggleForm = false;
    private String helper = "";

    public boolean isToggleForm() {
        return toggleForm;
    }

    public boolean setToggleForm() {
        toggleForm = !toggleForm;
        return toggleForm;
    }

    public String getHelper() {
        return helper;
    }

    public String getCurrentForm() {
        String temp = setToggleForm() ? "userLogin.xhtml" : "userRegister.xhtml";
        helper = temp;
        return temp;
    }

}
