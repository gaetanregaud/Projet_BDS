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
		// Retourne la liste des sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
			
		// Retourne la liste des équipes sport de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			

		// Récupère l'id de connexion et retourne la fiche étuidante de la BDD
			String identifiant = (String) request.getSession().getAttribute("user_id");
			Etudiant etudiant = Manager.getInstance().getEtudiant(identifiant);
			request.setAttribute("etudiant", etudiant);
			
		// Liste les pratiques de sport de l'étudiant
			List<Pratiquer> pratiquers = Manager.getInstance().listerPratiqueforEtudiant(identifiant);
			request.setAttribute("pratiquers", pratiquers);
		
		// Liste les séances du sport noté de l'étudiant
			List<Seance> seances = Manager.getInstance().listerSeancebyIdforEtudiant(identifiant);
			request.setAttribute("seances", seances);
		
		// Liste les challenges de l'étudiant dans lesquels il s'est inscrit
			List<Participer> participations =  Manager.getInstance().listerParticipationforEtudiant(identifiant);
			request.setAttribute("participations", participations);
			
		// Données du Calendrier + Liste des challenges
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
		
		// Affiche la page ficheEtudiant.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/ficheEtudiant.jsp");
			view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupère le type de la method post à affectuer
			String type = request.getParameter("type");
		
		//Récupère l'id de connexion de l'étudiant
		String id_etudiant = (String) request.getSession().getAttribute("user_id");
		
		
		if(type.equals("inscriptionEquipeSport")){//Method post pour s'inscrire à une équipe sport (AS)
			System.out.println(id_etudiant);
			String id_equipeSport = request.getParameter("sport");
			System.out.println(id_equipeSport);
			String note = request.getParameter("note");
			System.out.println(note);
			String noteR ="0";
			if(note.equals("oui")){
				noteR = "1";
			}
			System.out.println(noteR);
			Pratiquer pratique = new Pratiquer(id_etudiant, id_equipeSport, noteR);
			Manager.getInstance().ajouterPratique(pratique); //Ajoute l'inscription à l'équipe sport(AS) à la BDD
			response.sendRedirect("ficheetudiant");
		}
	}

}
