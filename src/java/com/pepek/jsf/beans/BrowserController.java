/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.jsf.beans;

//import jpa.entities.Flat;
///import databaseIntegration.FlatDAO;
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String search) {
        searchString = search;
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
