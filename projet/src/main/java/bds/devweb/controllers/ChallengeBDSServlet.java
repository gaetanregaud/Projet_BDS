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

import com.google.gson.Gson;

import bds.devweb.metier.Manager;
import bds.devweb.model.Adresse;
import bds.devweb.model.BDS;
import bds.devweb.model.Challenge;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Participer;
import bds.devweb.model.Sport;


public class ChallengeBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	private static DateFormat heureFormat = new SimpleDateFormat("hh:mm"); 
	
    public ChallengeBDSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retourne la liste des sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
			
		// Retourne la list des equipes sports (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			
		//Recupère l'id de connexion et Retourne la fiche du BDS de l'étudiant
			String identifiant = (String) request.getSession().getAttribute("user_id");
			BDS bds = Manager.getInstance().getBDS(identifiant);
			request.setAttribute("bds", bds);
			
		//Retourne la liste des challenges de la BDD
			List<Challenge> challenges = Manager.getInstance().listerChallenge();
			request.setAttribute("challenges", challenges);
		
		//Retourne la liste des adresses de la BDD
			List<Adresse> adresses = Manager.getInstance().listerAdresse();
			request.setAttribute("adresses", adresses);
			
		// Retourne la liste des participants aux challenges de la BDD
			List<Participer> participants = Manager.getInstance().listerParticipation();
			request.setAttribute("participants", participants);
			
		// Retourne la liste des noms de challenges 
			List<Challenge> typeschallenge = Manager.getInstance().listerTypeChallenge();
			request.setAttribute("typeschallenge", typeschallenge);
			
		//Affiche la page challenge.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/challengeBDS.jsp");
			view.forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupère le type de la method post pour savoir qu'elle method post aplliquer
		String type = request.getParameter("type");
		
		if(type.equals("newChallenge")){ //Methode pour la création d'un challenge
			String id_challenge = request.getParameter("id_challenge");
			String nom_challenge = request.getParameter("nom_challenge");
			String date = request.getParameter("date_challenge");
			Date date_challenge = null;
			try {
				date_challenge = dateFormat.parse(date);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String heure = request.getParameter("heure_challenge");
			Date heure_challenge = null;
			try {
				heure_challenge = heureFormat.parse(heure);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String description_challenge = request.getParameter("description");
			String id_adresse = request.getParameter("lieu");
			System.out.print(id_adresse);
			Challenge challenge = new Challenge(id_challenge, nom_challenge, date_challenge, heure_challenge, description_challenge, id_adresse);
			System.out.print(challenge);
			Manager.getInstance().ajouterChallenge(challenge); //Ajoute le challenge à la BDD
			response.sendRedirect("challengebds");
		}
		if(type.equals("suprChallenge")){ //Method pour supprimer un challenge existant
			String id_challenge = request.getParameter("id_challenge");
			Manager.getInstance().supprimerChallenge(id_challenge); // Supprime le challenge par rapport à son id de la BDD
			response.sendRedirect("challengebds");
		}
		if(type.equals("modifChallenge")){ //Method pour séléctionner et retourner le challenge à modifier
			String id_challenge = request.getParameter("id_challenge");
			Challenge challenge = Manager.getInstance().getChallenge(id_challenge); //Récupère le challenge par son id dans la BDD
			Gson gson = new Gson();
			String challengeJson = gson.toJson(challenge);
			response.setContentType("application/json");
			response.getWriter().write(challengeJson);//Renvoie le challenge en JSON pour le JS
			System.out.println("Ca marche");
		}
		if(type.equals("ajouChallenge")){ // Method pour récupérer les informations d'un challenge déja existant dans la BDD
			String nom_challenge = request.getParameter("nom_challenge");
			Challenge challenge = Manager.getInstance().getLastChallenge(nom_challenge); //Récupère les informations du dernier challenge ayant le nom
			Gson gson = new Gson();
			String challengeJson = gson.toJson(challenge);
			response.setContentType("application/json");
			response.getWriter().write(challengeJson); //Renvoie les informations du challenge en JSON pour le JS
			System.out.println("Ca marche");
		}
		if(type.equals("validerModifChallenge")){ //Method pour enregistrer les informations modifiés du challenge dans la BDD
			String id_challenge = request.getParameter("modifid_challenge");
			String nom_challenge = request.getParameter("modifnom_challenge");
			String date = request.getParameter("modifdate_challenge");
			Date date_challenge = null;
			try {
				date_challenge = dateFormat.parse(date);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String heure = request.getParameter("modifheure_challenge");
			Date heure_challenge = null;
			try {
				heure_challenge = heureFormat.parse(heure);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String description_challenge = request.getParameter("modifdescription");
			String id_adresse = request.getParameter("modiflieu");
			Challenge challenge = new Challenge(id_challenge, nom_challenge, date_challenge, heure_challenge, description_challenge, id_adresse);
			Manager.getInstance().modifierChallenge(challenge); //Enregistre les informations modifiés dans la BDD
			response.sendRedirect("challengebds");
		}
	}

}
