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
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Sport;


public class AccueilServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public AccueilServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retourne la liste des Sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
		//Retourne la liste es équipes de sport (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);

		// Données du Calendrier
			Date date = new Date();
			int mois = date.getMonth();
			int jour = date.getDate();
			request.setAttribute("mois", mois);
			request.setAttribute("jour", jour);
			int annee1 = date.getYear();
			int annee2 = annee1 - 1;
			//Retourne un calendrier entre Septembre et Aout en fonction de l'année de bébut et de fin
				List<DateCalendrier> calendriers = Manager.getInstance().calendrier(annee2, annee1);
				request.setAttribute("calendriers", calendriers);
		//Retourne la liste des challenges de la BDD
			List<Challenge> challenges =  Manager.getInstance().listerChallenge();
			request.setAttribute("challenges", challenges);
		
		//Afiche la page d'acceuil du site index.jsp
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/index.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
