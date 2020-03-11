/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;
import com.pepek.misc.SessionUtils;
import com.pepek.misc.Utilieties;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michal
 */
public class FasadaUser {

    private User user;
    private List<User> users = null;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
        public User Init(String username) {
        if (users == null || users.isEmpty()) {
            users = new ArrayList<User>();
        }
        for (User u : users) {
            if (u.getNazwaUzytkownika().contains(username)) {
                return getUser();
            }

        }

        HttpSession session = SessionUtils.getSession();
        Utilieties.Sex s = session.getAttribute("gender").toString().contains(Utilieties.Sex.K.toString()) ? Utilieties.Sex.K : Utilieties.Sex.M;
        User u = new User(Integer.parseInt(session.getAttribute("userid").toString()), session.getAttribute("username").toString(), session.getAttribute("email").toString(),
                Integer.parseInt(session.getAttribute("number").toString()),s, (Date) session.getAttribute("date"));
        users.add(u);
        setUser(u);
        return getUser();
    }
}
