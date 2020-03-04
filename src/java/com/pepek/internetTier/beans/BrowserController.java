package com.pepek.internetTier.beans;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.enitities.Users;
import com.pepek.integrationTier.facades.FlatstableFacade;
import com.pepek.integrationTier.facades.UsersFacade;
import com.pepek.misc.SessionUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

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
    private boolean showPopup;
    private Users owner;
    private Float size;
    private List<String> l_imagesPaths;
    private List<Flatstable> allFlats;
    private int renderedImagesFromFlatstable;

    @EJB
    private FlatstableFacade flatstableFacade;
    @EJB
    private UsersFacade usersFacade;

    private List<Flatstable> FlatsByString;

    public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Collection<Part> parts = new ArrayList<>();
        Collection<Part> col = request.getParts();

        for (Part p : col) {
            if (p.getName().equals(part.getName())) {
                parts.add(p);
            }
        }
        return parts;
    }

    public String addFlatString() {
        addFlat();
        try {
            FacesContext.getCurrentInstance().
                    getExternalContext().redirect("http://localhost:8080/faces/home.xhtml");

        } catch (IOException asd) {
            System.err.println(asd.getMessage());
        }
        return "userLogin";
    }



    public Flatstable addFlat() {

        List<String> imagesNames = new ArrayList<>();
        Path rootFolder = Paths.get(flatstableFacade.imagesRootpath);
        Random random = new Random();
        try {
            for (Part part : getAllParts(images)) {
                String fileName = flatstableFacade.count() + 1 + "_" + random.nextInt(1000000000);
                String extension = FilenameUtils.getExtension(part.getSubmittedFileName());

                InputStream fileContent = part.getInputStream();
                File file = new File("A:\\Users\\Michal\\Documents\\NetBeansProjects\\Git\\OtoNaszDom\\web\\resources\\images\\" + fileName + "." + extension);
                FileUtils.copyInputStreamToFile(fileContent, file);

                System.out.println("Uploaded file successfully saved in " + file.getAbsolutePath());
                imagesNames.add(fileName + "." + extension);

            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BrowserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //List<String> imagesPath = flatstableFacade.upload(files);
        getOwner();
        Flatstable ret = flatstableFacade.AddNewFlatToDB(owner, name, desc, price, imagesNames, size);

        return ret;

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
        price = null;
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
        String s = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

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

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public List<String> getL_imagesPaths(Flatstable flat) {
        List<String> temp = new ArrayList<>();
        String[] sTemp = flat.getS_imagesPaths().split("\n");

//        for (int i = 0; i < sTemp.length; i++) {
//            sTemp[i] = flatstableFacade.imagesRootpath + sTemp[i];
//        }
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

    public int getRenderedImagesFromFlatstable() {

        return (renderedImagesFromFlatstable <= 0) ? 0 : renderedImagesFromFlatstable;
    }

    public void setRenderedImagesFromFlatstable(int renderedImagesFromFlatstable) {
        this.renderedImagesFromFlatstable = renderedImagesFromFlatstable;
    }

}
