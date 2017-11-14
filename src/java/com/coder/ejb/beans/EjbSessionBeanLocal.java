package com.coder.ejb.beans;

import com.coder.web.model.Person;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EjbSessionBeanLocal {
        
        public List<Person> findPersonByName(String name);
        
        public List<Person> findAllPersons();
        
        public void addPerson(Person person);
        
        public void editPerson(Person person);
        
        public void deletePerson(int Id);
}
