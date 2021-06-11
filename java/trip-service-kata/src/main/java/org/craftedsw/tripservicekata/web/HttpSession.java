package org.craftedsw.tripservicekata.web;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class HttpSession {

	private static final HttpSession userSession = new HttpSession();
	
	private HttpSession() {
	}
	
	public static HttpSession getInstance() {
		return userSession;
	}

	public User getLoggedUser() {
		throw new CollaboratorCallException(
				"HttpSession should not be used in an unit test: it may have a dependency on a web context or on a database for instance");
	}

}
