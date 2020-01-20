/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.jsf.beans;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.pepek.misc.User;
import com.pepek.misc.Utilieties.Sex;
import database.RegisterDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 *@author Michal
 */
@ManagedBean
@SessionScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String user;
    private String haslo;
    private String email;
    private String adres;
    private int telefon;
    private Sex plec;
    private Blob awatar;
    private Date data;

    //validate login
    public String ValidateRegister() {
        boolean valid = RegisterDAO.InsertRegister(user, haslo, email, telefon, data, plec, adres, awatar);
        if (valid) {
//           HttpSession session = SessionUtils.getSession();
//           session.setAttribute("username", user);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane logowania",
                            "proszę wpisać poprawne dane logowania"));
            return "register";
        }
    }

    public static HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
        return requestFactory.buildGetRequest(url).execute();
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

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public Sex getPlec() {
        return plec;
    }

    public void setPlec(Sex plec) {
        this.plec = plec;
    }

    public Blob getAwatar() {
        return awatar;
    }

    public void setAwatar(Blob awatar) {
        this.awatar = awatar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
