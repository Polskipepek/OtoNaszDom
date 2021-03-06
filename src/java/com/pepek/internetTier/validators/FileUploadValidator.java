package com.pepek.internetTier.validators;

import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value = "com.pepek.internetTier.validators.fileUploadValidator")
public class FileUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;

        FacesMessage message = null;

        if (!continueValidation()) {
            return;
        }

        try {
            if (file == null || file.getSize() <= 0 || file.getContentType().isEmpty()) {
                message = new FacesMessage("Select a valid file");

            } else if (!file.getContentType().endsWith("jpg") && !file.getContentType().endsWith("png")
                    && !file.getContentType().endsWith("jpeg") && !file.getContentType().endsWith("ico") && !file.getContentType().endsWith("gif")) {
                message = new FacesMessage("Select an image file");
            } else if (file.getSize() > 10000000) {
                message = new FacesMessage("File size too big. File size allowed  is less than or equal to 10 MB.");
            }

            if (message != null && !message.getDetail().isEmpty()) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }

    }

    protected boolean continueValidation() {
        String skipValidator = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
        if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }

}
