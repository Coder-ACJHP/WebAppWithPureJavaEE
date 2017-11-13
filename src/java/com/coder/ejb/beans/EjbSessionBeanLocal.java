package com.coder.ejb.beans;

import javax.ejb.Local;

@Local
public interface EjbSessionBeanLocal {

	public String getUserDetails(String message);
}
