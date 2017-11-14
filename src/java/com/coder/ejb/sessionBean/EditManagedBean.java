/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.ejb.sessionBean;

import com.coder.ejb.beans.EjbSessionBeanLocal;
import com.coder.web.model.Person;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author MacbookPro
 */
@ManagedBean
@SessionScoped
public class EditManagedBean implements Serializable {

    private Person person;
    private Part photoPart;

    @EJB
    private EjbSessionBeanLocal beanLocal;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Part getPhotoPart() {
        return photoPart;
    }

    public void setPhotoPart(Part photoPart) {
        this.photoPart = photoPart;
    }

    public void reset() {
        person = new Person();
    }

    public EditManagedBean() {
    }

    public void attrListener(ActionEvent actionEvent) {
        person = (Person) actionEvent.getComponent().getAttributes().get("person");
    }

    public String editPerson(Person thePerson) {

        thePerson.setPHOTO(getEncodedString());
        beanLocal.editPerson(thePerson);
        return "index.xhtml?faces-redirect=true";
    }

    private String getEncodedString() {
        String baseEncoded = "";
        if (photoPart != null) {
            try {
                final InputStream inputStream = photoPart.getInputStream();
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
