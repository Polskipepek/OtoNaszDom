/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.jsf.beans;

import jpa.entities.Flat;
import databaseIntegration.FlatDAO;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Michal
 */
@ManagedBean
@SessionScoped
public class BrowserController {

    private String name;
    private String desc;
    private float price;
    private Image[] imgs;
    private Date data;

    private boolean showPopup;

    public void showPopupAddFlat() {
        showPopup = !showPopup;
        if (showPopup == false) {
            hidePopupAddFlat();
        }
    }

    public void hidePopupAddFlat() {
        name = "";
        desc = "";
        price = -1;
        imgs = null;
        data = null;

        showPopup = false;
    }

    private String searchString = "";
    private Flat[] flats;

    public Flat[] GetAllFlats() {
        flats = FlatDAO.GetFlats(searchString);

        Flat[] xd = new Flat[2];
        xd[0] = new Flat("mieszkaniako", "jakis tam opis", 12443f, null, new Date(399939439439l));
        xd[1] = new Flat("mieszka2ko", "jakis tam opis, zeby nie bylo", 52443f, null, new Date(499939439439l));
        return xd;
        //return flats;
    }

    public void AddFlat() {
        Flat flat = new Flat(name, desc, price, imgs, data);
        if (flat.getName().length() > 0 && flat.getDescriprion().length() > 0) {
            FlatDAO.AddFlat(flat);
            hidePopupAddFlat();
        }

    }

    public Flat[] GetFlatsByString(String input) {
        //TO DO
        return null;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String search) {
        searchString = search;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public void setFlats(Flat[] flats) {
        this.flats = flats;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Image[] getImgs() {
        return imgs;
    }

    public void setImgs(Image[] imgs) {
        this.imgs = imgs;
    }

    public Date getData() {
        return data;
    }

    public void setData() {
        data = new Date(LocalDateTime.now().getSecond());
    }

}
