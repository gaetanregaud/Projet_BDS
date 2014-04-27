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
import bds.devweb.model.Etudiant;
import bds.devweb.model.Participer;
import bds.devweb.model.Pratiquer;
import bds.devweb.model.Seance;
import bds.devweb.model.Sport;

public class FicheEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FicheEtudiantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
		// Fin Listes de la barre de menu du header
		//Donn��es relatives �� la page
			String identifiant = (String) request.getSession().getAttribute("user_id");
			Etudiant etudiant = Manager.getInstance().getEtudiant(identifiant);
			request.setAttribute("etudiant", etudiant);
			List<Pratiquer> pratiquers = Manager.getInstance().listerPratiquerforEtudiant(identifiant);
			request.setAttribute("pratiquers", pratiquers);
			System.out.println("je suis: "+pratiquers.get(0).getVp().getNom());
			List<Seance> seances = Manager.getInstance().listerSeancebyIdforEtudiant(identifiant);
			request.setAttribute("seances", seances);
			List<Participer> participations =  Manager.getInstance().listerParticipationforEtudiant(identifiant);
			request.setAttribute("participations", participations);
		// Donn��es du Calendrier
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
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/ficheEtudiant.jsp");
			view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
