package bds.devweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Participer;


public class ParticiperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ParticiperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_etudiant = (String) request.getSession().getAttribute("user_id");
		String id_challenge = request.getParameter("id_challenge");
		Participer participer = new Participer(id_etudiant, id_challenge, "-1");
		Manager.getInstance().ajouterParticipationToChallenge(participer);
		response.sendRedirect("ficheetudiant");
		
		
	}

}
