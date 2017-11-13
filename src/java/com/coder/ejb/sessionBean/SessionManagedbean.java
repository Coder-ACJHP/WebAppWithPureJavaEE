package com.coder.ejb.sessionBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.coder.ejb.beans.EjbSessionBeanLocal;

@ManagedBean
@SessionScoped
public class SessionManagedbean {

	private String message;
	
	@EJB
	private EjbSessionBeanLocal beanLocal;
	
	public SessionManagedbean() {}
	
        
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
        
	public String sayHelloToUser() {
            if(message != null)
                return beanLocal.getUserDetails(message).toString();
            return "";
	}
	
}
