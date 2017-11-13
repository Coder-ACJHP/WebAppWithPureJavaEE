package com.coder.ejb.beans;

import com.coder.web.model.Users;
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
    
    public EjbSessionBean() {}

	@Override
	public String getUserDetails(String message) {
		return em.find(Users.class, message).toString();
	}

}
