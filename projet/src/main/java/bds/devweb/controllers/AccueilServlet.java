package bds.devweb.controllers;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Challenge;
import bds.devweb.model.DateCalendrier;
import bds.devweb.model.Sport;


public class AccueilServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public AccueilServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> lisports = Manager.getInstance().listerLiSports();
			request.setAttribute("lisports", lisports);
		// Données du Calendrier
			Date date = new Date();
			int mois = date.getMonth();
			int jour = date.getDate();
			request.setAttribute("mois", mois);
			request.setAttribute("jour", jour);
			int annee1 = date.getYear();
			int annee2 = annee1 - 1;
			List<DateCalendrier> calendriers = Manager.getInstance().calendrier(annee2, annee1);
			request.setAttribute("calendriers", calendriers);
			List<Challenge> challenges =  Manager.getInstance().listerChallenge();
			request.setAttribute("challenges", challenges);
		//Affichage de la Page
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/index.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
