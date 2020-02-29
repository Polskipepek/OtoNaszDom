package com.pepek.internetTier.beans;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import com.pepek.integrationTier.facades.FlatstableFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Michal
 */
@ManagedBean
@SessionScoped
public class BrowserController {

    private String name;
    private String desc;
    private float price;
    private Part[] files;
    private Date data;
    private String searchString;
    private boolean showPopup;
    private Users owner;
    private float size;
    private List<String> l_imagesPaths;
    private List<Flatstable> allFlats;
    @EJB
    private FlatstableFacade flatstableFacade;
    private List<Flatstable> FlatsByString;

    public Flatstable AddFlat() {
        List<String> imagesPath = flatstableFacade.upload(files);
        return flatstableFacade.AddNewFlatToDB(owner, name, desc, price, imagesPath, size);

    }

    public void showPopupAddFlat() {
        showPopup = !showPopup;
        if (showPopup == false) {
            hidePopupAddFlat();
        }
    }

    public void hidePopupAddFlat() {
        name = "";
        desc = "";
        price = -1;
        files = null;
        data = null;

        showPopup = false;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String search) {
        searchString = search;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
        this.owner = owner;
    }

    public FlatstableFacade getFlatstableFacade() {
        return flatstableFacade;
    }

    public void setFlatstableFacade(FlatstableFacade flatstableFacade) {
        this.flatstableFacade = flatstableFacade;
    }

    public Part[] getFiles() {
        return files;
    }

    public void setFiles(Part[] files) {
        this.files = files;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public List<String> getL_imagesPaths(Flatstable flat) {
        List<String> temp = new ArrayList<>();
        String[] sTemp = flat.getS_imagesPaths().split("\n");
        temp.addAll(Arrays.asList(sTemp));

        l_imagesPaths = temp;
        return l_imagesPaths;
    }

    public void setL_imagesPaths(List<String> l_imagesPaths) {
        this.l_imagesPaths = l_imagesPaths;
    }

    public List<Flatstable> getAllFlats() {
        return flatstableFacade.GetAllFlats();
    }

    public void setAllFlats(List<Flatstable> allFlats) {
        this.allFlats = allFlats;
    }

    public List<Flatstable> getFlatsByString() {
        return flatstableFacade.GetFlats(getSearchString(), getOwner());
    }

    public void setFlatsByString(List<Flatstable> FlatsByString) {
        this.FlatsByString = FlatsByString;
    }
}
