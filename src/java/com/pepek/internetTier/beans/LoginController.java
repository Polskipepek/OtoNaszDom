package com.pepek.internetTier.beans;

import com.pepek.integrationTier.facades.UsersFacade;
import com.pepek.misc.SessionUtils;
import java.io.Serializable;
import javax.ejb.EJB;
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
            session.setAttribute("username", user);

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
