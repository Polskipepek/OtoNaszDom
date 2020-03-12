/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.internetTier.beans;

import com.pepek.businessTier.facades.UsersFacade;
import com.pepek.misc.Utilieties;
import com.pepek.misc.Utilieties.Sex;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/*
 *@author Michal
 */
@ManagedBean
@SessionScoped
public class RegisterController implements Serializable {

    @EJB
    private UsersFacade usersFacade;

    private static final long serialVersionUID = 1094838637898L;

    private String user;
    private String haslo;
    private String email;
    private String adres;
    private Integer telefon;
    private Sex plec;
    private File awatar;
    private String data;

    //validate
    public String validateRegister() {

        String valid = usersFacade.AddUserToDB(user, haslo, email, telefon, 
                new java.sql.Date(Utilieties.StringToDate(data).getTime()), plec);

        if (valid.equals("true")) {
            return "userLogin";

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane rejestracji. Problem: " + valid + "",
                            "proszę wpisać poprawne dane logowania "));

            return "false";
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
