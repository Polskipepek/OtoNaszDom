
/**
 *
 * @author Michal
 */
package Objects;
import com.pepek.misc.Utilieties;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String nazwaUzytkownika;
    private String mail;
    private Integer numer;
    private Utilieties.Sex plec;
    private Date data;
    private List<Flat> userflats;

    public User(int id, String nazwaUzytkownika, String mail, Integer numer, Utilieties.Sex plec, Date data) {
        this.id = id;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.mail = mail;
        this.numer = numer;
        this.plec = plec;
        this.data = data;
    }
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public Utilieties.Sex getPlec() {
        return plec;
    }

    public void setPlec(Utilieties.Sex plec) {
        this.plec = plec;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Flat> getUserflats() {
        return userflats;
    }

    public void setUserflats(List<Flat> userflats) {
        this.userflats = userflats;
    }
    
}
