/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.jsf.beans;

import com.pepek.jpa.entities.Flat;
import java.awt.Image;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Michal
 */
@ManagedBean
@RequestScoped
public class BrowserController {

    private String searchString;
    private Flat[] flats;

    public Flat[] GetAllFlats() {

        return null;
    }

    public void AddFlat(String name, String desc, float price, Image[] imgs) {

    }

    public void SearchByString(String input) {
        //TO DO
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

}
