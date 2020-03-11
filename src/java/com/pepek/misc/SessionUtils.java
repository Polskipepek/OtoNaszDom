/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.misc;

import Objects.User;
import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michal
 */
public class SessionUtils {

    private User UserObject;

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }
    
    public static List<String> Errors = new ArrayList<>();

    public static String GetErrorFromCreate(String s) {
        Errors.add(s);
        return s;

    }

    private static Flatstable currFlat;

    public static Flatstable getCurrFlat() {
        return currFlat;
    }

    public static void setCurrFlat(Flatstable currFlat) {
        SessionUtils.currFlat = currFlat;
    }

    public User getUserObject() {
        return UserObject;
    }

    public void setUserObject(Users us) {
        User user = new User(Integer.parseInt(getUserId()), us.getUsername(), us.getEmail(), us.getNumber(), Utilieties.Sex.M, us.getDate());

        this.UserObject = user;
    }

}
