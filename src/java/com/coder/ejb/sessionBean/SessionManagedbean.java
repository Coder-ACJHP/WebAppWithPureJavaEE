package com.coder.ejb.sessionBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.coder.ejb.beans.EjbSessionBeanLocal;
import com.coder.web.model.Person;
import com.coder.web.util.HelperClass;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class SessionManagedbean implements Serializable {

    private String message;
    private Person person;
    private Part photoPart;
    private HelperClass helperClass;
    
    @EJB
    private EjbSessionBeanLocal beanLocal;
    
    @PostConstruct
    public void initialize() {
        person = new Person();
    }
    
    public SessionManagedbean() {
        helperClass = new HelperClass();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
    
    public void addPerson(){
        
        person.setPHOTO(helperClass.getEncodedString(photoPart));
        beanLocal.addPerson(person);
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
    }
    
    public List<Person> getPersonList() {
        if (message != null && message.trim().length() > 0) {
            return beanLocal.findPersonByName(message);
        } else {
            return beanLocal.findAllPersons();
        }
    }
  
    
    public void deletePerson(int Id) {
        beanLocal.deletePerson(Id);
    }

    public void reset() {
        person = new Person();
    }
}
