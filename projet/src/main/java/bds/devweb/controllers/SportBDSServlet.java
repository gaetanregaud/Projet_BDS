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
import bds.devweb.model.Sport;
import bds.devweb.model.VP;


public class SportBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SportBDSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
		//Donn��es relatives �� la page
			List<VP> listevps = Manager.getInstance().listerVP();
			request.setAttribute("listevps", listevps);
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport", listeEquipeSport);
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/sportBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("newSport")){
			String id_sport = request.getParameter("id_sport");
			String nom_sport = request.getParameter("nom_sport");
			String id_equipeSport = request.getParameter("id_equipeSport");
			String nom_equipeSport = request.getParameter("nom_equipeSport");
			String id_etudiant = request.getParameter("vp");
			Manager.getInstance().ajouterSport(id_sport, nom_sport);
			Manager.getInstance().ajouterEquipeSport(id_equipeSport, nom_equipeSport, "", id_sport);
			Manager.getInstance().ajouterVP(id_etudiant, id_equipeSport);
			System.out.println("Ajout Sport réussi");
		}
		if(type.equals("newEquipe")){
			String id_sport = request.getParameter("id_sport");
			String id_equipeSport = request.getParameter("id_equipeSport");
			String nom_equipeSport = request.getParameter("nom_equipeSport");
			String id_etudiant = request.getParameter("vp");
			Manager.getInstance().ajouterEquipeSport(id_equipeSport, nom_equipeSport, "", id_sport);
			Manager.getInstance().ajouterVP(id_etudiant, id_equipeSport);
			System.out.println("Ajout Equipe Sport réussi");
		}
		if(type.equals("suprSport")){
			String id_sport = request.getParameter("id_sport");
			Manager.getInstance().supprimerSport(id_sport);
			System.out.println("Supression sport réussi");
		}
		if(type.equals("suprEquipe")){
			String id_equipeSport = request.getParameter("id_equipeSport");
			Manager.getInstance().supprimerEquipeSport(id_equipeSport);
			System.out.println("Supression Equipe Sport réussi");
		}
		if(type.equals("modifVP")){
			String id_equipeSport = request.getParameter("modifequipesport");
			String id_etudiant = request.getParameter("vp");
			Manager.getInstance().modifierVPforEquipeSport(id_equipeSport, id_etudiant);
			System.out.println("Modification VP réussi");
		}
		response.sendRedirect("sportbds");
	}

}
