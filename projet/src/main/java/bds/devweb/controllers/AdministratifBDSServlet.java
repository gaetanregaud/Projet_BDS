package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bds.devweb.metier.Manager;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Sport;


public class AdministratifBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministratifBDSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retourne la liste des Sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
			
		//Retourne la liste es équipes de sport (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);

		//Retourne la liste des étudiants de la BDD
			List<Etudiant> listeEtudiant = Manager.getInstance().listerEtudiant();
			request.setAttribute("listeEtudiant", listeEtudiant);
			
		//Affiche la page administratif.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/administratifBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type"); //Récupère le type pour savoir qu'elle method post doit être exécuté
		if(type.equals("recherche_etudiant")){ //Exécution du post de la recherche etudiant
			String id_etudiant = request.getParameter("id_etudiant");
			Etudiant etudiant = Manager.getInstance().getEtudiant(id_etudiant); //Récupère la fiche étudiante correspondant à l'id
			Gson gson = new Gson();
			String etudiantJson = gson.toJson(etudiant); //Transforme la fiche etudiante en JSON
			response.setContentType("application/json");
			response.getWriter().write(etudiantJson); //Renvoie de la réponse en Json pour le JS
			System.out.println("Ca marche");
		}
		if(type.equals("modif_infoEtudiant")){ //Exucution du post modifier un étudiant
			String id_etudiant = request.getParameter("id_etudiant");
			String cotisation = request.getParameter("cotisation");
			String certificat = request.getParameter("certificat");
			String licence = request.getParameter("licence");
			Boolean cotis = false;
			if(cotisation.equals("oui")){
				cotis = true;
			}
			Boolean certif = false;
			if(certificat.equals("oui")){
				certif = true;
			}
			Etudiant etudiant = Manager.getInstance().getEtudiant(id_etudiant);
			Etudiant etudiant1 = new Etudiant(id_etudiant, etudiant.getNom(), etudiant.getPrenom(), etudiant.getClasse(), etudiant.getTelephone(), etudiant.getMail(), etudiant.getPhoto(), cotis, certif, licence);
			Manager.getInstance().modifEtudiant(etudiant1); //Modifie la fiche étudiante dans la BDD
			System.out.println("Ca marche");
		}
	}

}
