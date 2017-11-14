/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.ejb.sessionBean;

import com.coder.ejb.beans.EjbSessionBeanLocal;
import com.coder.web.model.Person;
import com.coder.web.util.HelperClass;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

/**
 *
 * @author MacbookPro
 */
@ManagedBean
@SessionScoped
public class EditManagedBean implements Serializable {

    private Person person;
    private Part photoPart;
    private HelperClass helperClass;
    

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
        helperClass = new HelperClass();
    }

    public void attrListener(ActionEvent actionEvent) {
        try {
            person = (Person) actionEvent.getComponent().getAttributes().get("person");
            FacesContext.getCurrentInstance().getExternalContext().redirect("edit.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.toString(), ex.getMessage()));
        }
    }

    public void editPerson() {

        person.setPHOTO(helperClass.getEncodedString(photoPart));
        beanLocal.editPerson(person);
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.toString(), ex.getMessage()));
        }
    }
}
