package com.coder.ejb.beans;

import com.coder.web.model.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class EjbSessionBean
 */
@Stateless
public class EjbSessionBean implements EjbSessionBeanLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    public EjbSessionBean() {
    }
    
    @Override
    public List<Person> findPersonByName(String name) {
        return em.createQuery("from Person where lower(NAME) LIKE:name", Person.class)
                .setParameter("name", "%"+name+"%")
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Person> findAllPersons() {
        return em.createQuery("from Person", Person.class).getResultList();
    }

    @Override
    public void editPerson(Person person) {
        em.merge(person);
    }

    @Override
    public void deletePerson(int Id) {
        Person person = em.find(Person.class, Id);
        em.remove(person);
    }

    @Override
    public void addPerson(Person person) {
        em.persist(person);
    }
    
}
