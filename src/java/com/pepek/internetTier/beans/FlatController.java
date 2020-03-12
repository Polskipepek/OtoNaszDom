package com.pepek.internetTier.beans;

import com.pepek.integrationTier.enitities.Flatstable;
import com.pepek.integrationTier.facades.FlatstableFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FlatController implements Serializable {

    @EJB
    private FlatstableFacade flatstableFacade;

    private Flatstable flatstable;

    public FlatstableFacade getFlatstableFacade() {
        return flatstableFacade;
    }

    public void setFlatstableFacade(FlatstableFacade flatstableFacade) {
        this.flatstableFacade = flatstableFacade;
    }

    public Flatstable getFlatstable() {
        return flatstable;
    }

    public void setFlatstable(Flatstable flatstable) {
        this.flatstable = flatstable;
    }

}
