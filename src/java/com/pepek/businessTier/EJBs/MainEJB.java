/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepek.businessTier.EJBs;


import com.pepek.integrationTier.facades.FlatstableFacade;
import com.pepek.integrationTier.facades.UsersFacade;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Michal
 */
@Stateless
public class MainEJB {

    @EJB
    private UsersFacade usersFacade;

    @EJB
    private FlatstableFacade flatstableFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. "
                    + e.getMessage());
        }
        return fileContent;
    }

}
