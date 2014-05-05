package bds.devweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeconnexionServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Deconnexion...");
		HttpSession session = request.getSession(true); 
		session.invalidate(); //Déconnecte la session actuellement connecté
		System.out.println("Session ferm��e");
		response.sendRedirect("accueil"); //Renvoie sur la servlet accueil
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
