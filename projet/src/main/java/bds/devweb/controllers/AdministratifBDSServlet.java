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
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
		//Donn��es relatives �� la page
			List<Etudiant> listeEtudiantNotOK = Manager.getInstance().listerEtudiantNotOk();
			request.setAttribute("listeEtudiantNotOK", listeEtudiantNotOK);
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/administratifBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("recherche_etudiant")){
			String id_etudiant = request.getParameter("id_etudiant");
			Etudiant etudiant = Manager.getInstance().getEtudiant(id_etudiant);
			Gson gson = new Gson();
			String etudiantJson = gson.toJson(etudiant);
			response.setContentType("application/json");
			response.getWriter().write(etudiantJson);
			System.out.println("Ca marche");
		}
		if(type.equals("modif_infoEtudiant")){
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
			Manager.getInstance().modifEtudiant(etudiant1);
			System.out.println("Ca marche");
		}
	}

}
