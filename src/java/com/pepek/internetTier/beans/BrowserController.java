package com.pepek.internetTier.beans;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import com.pepek.integrationTier.facades.FlatstableFacade;
import com.pepek.integrationTier.facades.UsersFacade;
import com.pepek.misc.SessionUtils;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Michal
 */
@ManagedBean
@SessionScoped
public class BrowserController implements Serializable {

    private String name;
    private String desc;
    private Float price;
    private Part images;
    private Date data;
    private String searchString;
    private Users owner;
    private Float size;
    private List<String> l_imagesPaths;
   // private List<Flatstable> allFlats;
    private int renderedImagesFromFlatstableCount;

    @EJB
    private FlatstableFacade flatstableFacade;
    @EJB
    private UsersFacade usersFacade;

  //  private List<Flatstable> FlatsByString;

    public Flatstable addFlat() {
        return flatstableFacade.AddFlatToDB(images,name,desc,price,size);

    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String search) {
        searchString = search;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date date) {
        data = date == null ? new Date(LocalDateTime.now().getSecond()) : date;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        owner = usersFacade.GetUser(SessionUtils.getUserName());
        this.owner = owner;
    }

    public FlatstableFacade getFlatstableFacade() {
        return flatstableFacade;
    }

    public void setFlatstableFacade(FlatstableFacade flatstableFacade) {
        this.flatstableFacade = flatstableFacade;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public List<String> getL_imagesPaths(Flatstable flat) {
        List<String> temp = new ArrayList<>();
        String[] sTemp = flat.getS_imagesPaths().split("\n");

        temp.addAll(Arrays.asList(sTemp));

        setL_imagesPaths(temp);
        return l_imagesPaths;
    }

    public void setL_imagesPaths(List<String> l_imagesPaths) {
        this.l_imagesPaths = l_imagesPaths;
    }

    public List<Flatstable> getAllFlats() {
        return flatstableFacade.GetAllFlats();
    }

    public List<Flatstable> getFlatsByString() {
//TODO//
        return flatstableFacade.GetFlats(getSearchString(), getOwner());
    }

//    public void setFlatsByString(List<Flatstable> FlatsByString) {
//        this.FlatsByString = FlatsByString;
//    }

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public Part getImages() {
        return images;
    }

    public void setImages(Part images) {
        this.images = images;
    }

    public int getRenderedImagesFromFlatstableCount() {
        return (renderedImagesFromFlatstableCount <= 0) ? 0 : renderedImagesFromFlatstableCount;
    }

    public void setRenderedImagesFromFlatstableCount(int renderedImagesFromFlatstableCount) {
        this.renderedImagesFromFlatstableCount = renderedImagesFromFlatstableCount;
    }
    public String addFlatString(String owner) {
        this.owner = usersFacade.GetUser(owner);
        addFlat();
        
        try {
            FacesContext.getCurrentInstance().
                    getExternalContext().redirect("http://localhost:8080/faces/home.xhtml");

        } catch (IOException asd) {
            System.err.println(asd.getMessage());
        }
        return "userLogin";
    }
}
