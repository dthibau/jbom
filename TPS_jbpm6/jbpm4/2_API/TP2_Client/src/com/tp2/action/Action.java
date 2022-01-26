package com.tp2.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {

	
	public abstract String perform(HttpServletRequest hreq);


}
