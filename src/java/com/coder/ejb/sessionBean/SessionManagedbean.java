package com.coder.ejb.sessionBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.coder.ejb.beans.EjbSessionBeanLocal;
import com.coder.web.model.Person;
import java.util.List;

@ManagedBean
@SessionScoped
public class SessionManagedbean {

    private String message;
    private Person person;
    
    @EJB
    private EjbSessionBeanLocal beanLocal;

    public SessionManagedbean() {
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
    
    public void addPerson() {
        beanLocal.addPerson(person);
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

}
