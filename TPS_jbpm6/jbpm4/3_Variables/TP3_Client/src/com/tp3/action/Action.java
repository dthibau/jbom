package com.tp3.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {

	
	public abstract String perform(HttpServletRequest hreq);


}
