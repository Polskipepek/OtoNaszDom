package com.pepek.internetTier.beans;

import com.pepek.businessTier.facades.UsersFacade;
import com.pepek.misc.SessionUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 *@author Michal
 */
@ManagedBean
@ApplicationScoped
public class LoginController implements Serializable {

    @EJB
    private UsersFacade usersFacade;

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {

        String valid = "";
        valid = usersFacade.LoginAuthentication(user, pwd);
        if (valid.contains("true")) {
            //Używamy sesji, aby przy wylogoweaniu zakończyć / unieważnić sesje, 
            //w celu uniemożliwienia w przypadku powrotu do poprzedniej strony modyfikacji danych i obsługi zdarzeń
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("userid", usersFacade.GetUser(user).getId());

            session.setAttribute("username", usersFacade.GetUser(user).getUsername());
            session.setAttribute("address", usersFacade.GetUser(user).getAddress());
            session.setAttribute("avatar", usersFacade.GetUser(user).getAvatar());
            session.setAttribute("email", usersFacade.GetUser(user).getEmail());
            session.setAttribute("flats", usersFacade.GetUser(user).getUserflats());
            session.setAttribute("number", usersFacade.GetUser(user).getNumber());
            session.setAttribute("gender", usersFacade.GetUser(user).getSex());
            session.setAttribute("date", usersFacade.GetUser(user).getDate());

            return "/home";

        } else if (valid.contains("notExist")) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Użytkownik nie istnieje",
                            "proszę wpisać poprawne dane logowania lub stworzyć nowego użytkownika"));

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane logowania",
                            "proszę wpisać poprawne dane logowania"));

        }
        return "";
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }
}
