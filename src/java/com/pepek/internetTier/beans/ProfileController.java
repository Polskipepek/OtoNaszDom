package com.pepek.internetTier.beans;
import Objects.FasadaUser;
import Objects.User;
import com.pepek.misc.Utilieties.Sex;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/*
 *@author Michal
 */
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    private static final long serialVersionUID = 10989637898L;
    private FasadaUser fasadaUser;
    private String username;
    private String email;
    private String adres;
    private Integer telefon;
    private Sex plec;
    private File awatar;
    private Date date;

    public User Initialize() {
        User us = getFasadaUser().Init(username);
        setDate(new java.sql.Date(us.getData().getTime()));
        setEmail(us.getMail());
        setPlec(us.getPlec());
        setTelefon(us.getNumer());
        setUsername(us.getNazwaUzytkownika());
        return us;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getAwatar() {
        return awatar;
    }

    public void setAwatar(File awatar) {
        this.awatar = awatar;
    }

    public FasadaUser getFasadaUser() {
        if (fasadaUser == null) {
            fasadaUser = new FasadaUser();
        }
        return fasadaUser;
    }

    public void setFasadaUser(FasadaUser fasadaUser) {
        this.fasadaUser = fasadaUser;
    }

}
