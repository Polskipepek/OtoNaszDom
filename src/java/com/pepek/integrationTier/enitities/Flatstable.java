/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.integrationTier.enitities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michal
 */
@Entity
@Table(name = "FLATSTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flatstable.findAll", query = "SELECT f FROM Flatstable f")
    , @NamedQuery(name = "Flatstable.findById", query = "SELECT f FROM Flatstable f WHERE f.id = :id")
    , @NamedQuery(name = "Flatstable.findByName", query = "SELECT f FROM Flatstable f WHERE f.name = :name")
    , @NamedQuery(name = "Flatstable.findByDescription", query = "SELECT f FROM Flatstable f WHERE f.description = :description")
    , @NamedQuery(name = "Flatstable.findByPrice", query = "SELECT f FROM Flatstable f WHERE f.price = :price")})
public class Flatstable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SIZE")
    private Float size;
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "IMAGE")
    private String s_imagesPaths;

    @ManyToOne
    private Users user;

    public Flatstable() {
    }

    public Flatstable(Integer id) {
        this.id = id;
    }

    public Flatstable(Integer id, String name, String description, Float size, Float price, String imagesPaths, Users user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.s_imagesPaths = imagesPaths;
        this.user = user;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flatstable)) {
            return false;
        }
        Flatstable other = (Flatstable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enitities.Flatstable[ id=" + id + " ]";
    }

    public boolean compareWithQuery(String query, Users owner) {
        return Objects.equals(owner.getId(), this.getId())
                && (this.getName().contains(query) || this.getDescription().contains(query));
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getS_imagesPaths() {
        return s_imagesPaths;
    }

    public void setS_imagesPaths(String s_imagesPaths) {
        this.s_imagesPaths = s_imagesPaths;
    }
}
