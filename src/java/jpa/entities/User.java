/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import com.pepek.misc.Utilieties;
import java.awt.Image;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Michal
 */
@Entity
public class User {
    
    @Id
    private int id;
    private String username, password, email, address;
    private Utilieties.Sex sex;
    private Date date;
    private Blob obraz;
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Utilieties.Sex getSex() {
        return sex;
    }

    public void setSex(Utilieties.Sex sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Blob getObraz() {
        return obraz;
    }

    public void setObraz(Blob obraz) {
        this.obraz = obraz;
    }
}
