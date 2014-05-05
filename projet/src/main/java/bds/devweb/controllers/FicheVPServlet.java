package bds.devweb.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Pratiquer;
import bds.devweb.model.Seance;
import bds.devweb.model.Sport;
import bds.devweb.model.VP;

public class FicheVPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

    public FicheVPServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retourne la liste des sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
		
		// Retourne la liste des équipes sports (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			
		// Récupère l'id_etudiant, l'id_sport et l'id_equipeSport(As) du VP de la connexion et retourne sa fiche VP
			String identifiant = (String) request.getSession().getAttribute("user_id");
			String id_sport = (String) request.getSession().getAttribute("user_sport");
			String id_equipeSport = (String) request.getSession().getAttribute("user_equipeSport");
			VP vp = Manager.getInstance().getVP(identifiant);
			request.setAttribute("vp", vp);
			
		// Retourne la Liste des pratiquants de la BDD de l'equipe sport du VP
			List<Pratiquer> pratiquants = Manager.getInstance().listerPratiquantbyEquipeSport(id_equipeSport);
			request.setAttribute("pratiquants", pratiquants);
			
		// Retourne la liste des equipes sports (AS) appartenant au même groupe de sport
			List<EquipeSport> equipessport = Manager.getInstance().listerEquipeSportbySport(id_sport);
			request.setAttribute("equipessport", equipessport);
			
		// Retourne la liste des séances dans l'ordre chronologique des séances
			List<Seance> seancesbynum = Manager.getInstance().listerSeancebyNumforEquipeSport(id_equipeSport);
			request.setAttribute("seancesbynum", seancesbynum);
			
		// Retourne la liste des séances par Etudiant dans l'odre chronoligique
			List<Seance> seancesbyid = Manager.getInstance().listerSeancebyIdforEquipeSport(id_equipeSport);
			request.setAttribute("seancesbyid", seancesbyid);
			
		// Retourne la liste des numéros de séance effectuer par l'équipe Sport
			List<Seance> numseances = Manager.getInstance().listerNumeroSeancebyEquipeSport(id_equipeSport);
			request.setAttribute("numseances", numseances);
			
		// Affiche la page ficheVp.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/ficheVP.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Method post pour ajouter une séance et la présence des étudiants
		String id_equipeSport = (String) request.getSession().getAttribute("user_equipeSport");
		String id_seance = request.getParameter("numseance");
		String datejsp = request.getParameter("date");
		List<Pratiquer> pratiquant = Manager.getInstance().listerPratiquantbyEquipeSport(id_equipeSport);
		Date date = null;
		try {
			date = dateFormat.parse(datejsp);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		for(int i = 0; i < pratiquant.size();i++){ //Liste les pratiquants de l'AS
			if(pratiquant.get(i).getNote().equals("1")){ //Verifie si l'étudiant veut une note
				String id_etudiant = pratiquant.get(i).getId_etudiant();
				String presence = request.getParameter("presence_"+id_etudiant);
				Seance seance = new Seance(id_seance, id_etudiant, id_equipeSport, date, presence);
				Manager.getInstance().ajouterSeance(seance); //Ajoute la séance et la participation de l"étudiant
			}
		}
		response.sendRedirect("fichevp"); //Renvoie a la servlet fichevp
	}

}
