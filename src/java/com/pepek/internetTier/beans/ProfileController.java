/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.internetTier.beans;

import com.pepek.integrationTier.facades.UsersFacade;
import com.pepek.misc.Utilieties.Sex;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 *@author Michal
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {

    @EJB
    private UsersFacade usersFacade;

    private static final long serialVersionUID = 1098637898L;

    private String user;
    private String email;
    private String adres;
    private Integer telefon;
    private Sex plec;
    private File awatar;
    private Date data;



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public Sex getPlec() {
        return plec;
    }

    public void setPlec(Sex plec) {
        this.plec = plec;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public File getAwatar() {
        return awatar;
    }

    public void setAwatar(File awatar) {
        this.awatar = awatar;
    }

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

}

