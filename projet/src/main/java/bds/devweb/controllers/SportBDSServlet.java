package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Sport;
import bds.devweb.model.VP;


public class SportBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SportBDSServlet() {
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
		
		// Retourne la liste de tout les VPs
			List<VP> listevps = Manager.getInstance().listerVP();
			request.setAttribute("listevps", listevps);
			
		//Affiche la page sportBDS.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/sportBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupère le type de la method post à effectuer
			String type = request.getParameter("type");
			
		// Verifie si l'id étudiant existe dans la BDD
			if(type.equals("verifIdentifiant")){
				List<Etudiant> listeEtudiant = Manager.getInstance().listerEtudiant();
				String id_etudiant = request.getParameter("id_etudiant");
				for(int i=0; i<listeEtudiant.size(); i++){
					if(listeEtudiant.get(i).getIdentifiant().equals(id_etudiant)){
						response.getWriter().append("oui");
					}	
				}
			}
		
		//Method post pour ajouter un nouveau sport et la nouvelle equipe sport ainsi que le VP associé
			if(type.equals("newSport")){
				String id_sport = request.getParameter("id_sport");
				String nom_sport = request.getParameter("nom_sport");
				String id_equipeSport = request.getParameter("id_equipeSport");
				String nom_equipeSport = request.getParameter("nom_equipeSport");
				String id_etudiant = request.getParameter("vp");
				Sport sport = new Sport(id_sport, nom_sport);
				Manager.getInstance().ajouterSport(sport); //Ajout du sport à la BDD
				EquipeSport equipesport = new EquipeSport(id_sport, "", id_equipeSport, nom_equipeSport, "", "");
				Manager.getInstance().ajouterEquipeSport(equipesport); //Ajout de l'équipe Sport à la BDD
				Manager.getInstance().ajouterVPForNewEquipeSport(id_etudiant, id_equipeSport); //ajout du nouveau VP de l'équipe Sport à la BDD
				System.out.println("Ajout Sport réussi");
			}
			
		// Method post pour l'ajout d'une nouvelle equipe sport et du VP associé à un sport existant
			if(type.equals("newEquipe")){
				String id_sport = request.getParameter("id_sport");
				String id_equipeSport = request.getParameter("id_equipeSport");
				String nom_equipeSport = request.getParameter("nom_equipeSport");
				String id_etudiant = request.getParameter("vp");
				EquipeSport equipesport = new EquipeSport(id_sport, "", id_equipeSport, nom_equipeSport, "", "");
				Manager.getInstance().ajouterEquipeSport(equipesport); //Ajout de l'équipe sport à la BDD
				Manager.getInstance().ajouterVPForNewEquipeSport(id_etudiant, id_equipeSport); //Ajout du Vp associé à l'équipe Sport à la BDD
				System.out.println("Ajout Equipe Sport réussi");
			}
			
		// Method post pour supprimer un sport ainsi que les équipes et VP associé à ce sport
			if(type.equals("suprSport")){
				String id_sport = request.getParameter("id_sport");
				Manager.getInstance().supprimerSport(id_sport); //Supprime le sport, les equipes et les vp de la BDD
				System.out.println("Supression sport réussi");
			}
			
		// Method post pour supprimer une équipe sport et le vp associé
			if(type.equals("suprEquipe")){
				String id_equipeSport = request.getParameter("id_equipeSport");
				Manager.getInstance().supprimerEquipeSport(id_equipeSport); //supprime l'équipe et le vp de la BDD
				System.out.println("Supression Equipe Sport réussi");
			}
			
		// Method post pour modifier un vp d'une équipe sport existant
			if(type.equals("modifVP")){
				String id_equipeSport = request.getParameter("modifequipesport");
				String id_etudiant = request.getParameter("vp");
				Manager.getInstance().modifierVPForExistEquipeSport(id_etudiant, id_equipeSport);
				System.out.println("Modification VP réussi");
			}

		// Renvoie sur la servlet sportbds
			response.sendRedirect("sportbds");
	}

}
