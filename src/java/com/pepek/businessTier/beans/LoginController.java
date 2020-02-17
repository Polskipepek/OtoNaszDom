/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.beans;


import com.pepek.businessTier.EJBs.MainEJB;
import com.pepek.misc.SessionUtils;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 *@author Michal
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

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

    MainEJB mainEJB;

    //validate login
    public String validateUsernamePassword() {

        boolean valid = false;
        valid = mainEJB.LoginAuthentication(user, pwd);
        if (valid) {
            //Używamy sesji, aby przy wylogoweaniu zakończyć / unieważnić sesje, 
            //w celu uniemożliwienia w przypadku powrotu do poprzedniej strony modyfikacji danych i obsługi zdarzeń
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            
            return "/home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane logowania",
                            "proszę wpisać poprawne dane logowania"));
            return "";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

}
/*

    public String GoogleLoggedIn(String accessToken) {
        int a = 5;
        a++;
        return null;
    }


    public static HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
        return requestFactory.buildGetRequest(url).execute();
    }

*/