/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.internetTier.beans;

import com.pepek.businessTier.EJBs.MainEJB;
import com.pepek.misc.Utilieties.Sex;
import java.io.File;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
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

    private static final long serialVersionUID = 1094838637898L;

    private String user;
    private String haslo;
    private String email;
    private String adres;
    private int telefon;
    private Sex plec;
    private File awatar;
    private Date data;

    MainEJB mainEJB;

    //validate
    public String validateRegister() throws SQLException, NoSuchAlgorithmException {
        boolean valid = true;
        mainEJB.AddUserToDB(user, haslo, email, telefon, data, plec, adres, awatar);
        if (valid) {
            return "userLogin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane rejestracji",
                            "proszę wpisać poprawne dane logowania"));
            return "";
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

}
/*
    public static HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
        return requestFactory.buildGetRequest(url).execute();
    }


 */
