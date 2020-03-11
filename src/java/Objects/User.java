/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import com.pepek.misc.Utilieties;
import java.util.Date;

/**
 *
 * @author Michal
 */
public class User {
    private int id;
    private String nazwaUzytkownika;
    private String adres;
    private String mail;
    private Integer numer;
    private Utilieties.Sex plec;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public Utilieties.Sex getPlec() {
        return plec;
    }

    public void setPlec(Utilieties.Sex plec) {
        this.plec = plec;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
