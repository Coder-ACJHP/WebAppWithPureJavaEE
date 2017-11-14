/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.web.util;

import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author MacbookPro
 */
public class HelperClass implements Serializable {
    
     public String getEncodedString(Part part) {
        String baseEncoded = "";
        if (part != null) {
            try {
                final InputStream inputStream = part.getInputStream();
                byte[] photo = IOUtils.toByteArray(inputStream);
                byte[] encode = Base64.encodeBase64(photo);
                baseEncoded = new String(encode);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), e.getMessage()));
            }
        }
        return baseEncoded;
    }
}
