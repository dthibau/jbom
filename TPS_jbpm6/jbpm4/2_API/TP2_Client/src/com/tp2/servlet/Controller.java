package com.tp2.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp2.action.Action;

public class Controller extends HttpServlet {
	public final static ResourceBundle actionBundle = ResourceBundle.getBundle("actions");
	
	// Http parameters
	public static String PDF_PARAM="pdf";  // identifiant d'une d�finition de processus
	public static String PINST_NAME_PARAM = "pInstName"; // Le nom d'une instance
	public static String PINST_PARAM ="pInstId"; // L'identifiant d'une instance
	public static String TOKEN_PARAM = "tokenId"; // L'identifiant d'un jeton
	public static String TRANSITION_PARAM = "transitionName"; // L'identifiant d'un jeton
	
		
	// Vues
	public static String PDF_LIST_VIEW = "pdfList.jsp"; // Liste des d�finitions
	public static String PINST_LIST_VIEW = "piList.jsp"; // Liste des instances de processus
	public static String PINST_CREATE_FORM = "piForm.jsp"; // Formulaire de cr�ation d'instance


	/**
	 * 
	 */
	private static final long serialVersionUID = 4171068430066757139L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String view = executeAction(req);
		
		RequestDispatcher rd =
	         req.getRequestDispatcher(view);

	    // Forward to requested URL
	    rd.forward(req, resp);

	}
	
	private String executeAction(HttpServletRequest req) {
		String op = actionBundle.getString(req.getServletPath());

		
		String result = null;
		try {
			Action action = (Action)Class.forName(op).newInstance();
			result = action.perform(req);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;

	}
	

}
