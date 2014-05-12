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



public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ValidationServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retourne la liste des sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
			
		// Retourne la liste des equipes Sport (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			
		// Affiche la page de connexion.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/validation.jsp");
			view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		String confirmMessage = "";
		String errorMessage = "";
		String bienvenue = "";
		Manager.getInstance().ValidationEtudiant(identifiant, password);
		if(Manager.getInstance().seConnecterEtudiant(identifiant, password)){
			String compte = "etudiant";
			request.getSession().setAttribute("user_type", compte);
			request.getSession().setAttribute("user_id", request.getParameter("identifiant"));
			Etudiant etudiant = Manager.getInstance().getEtudiant((String) request.getSession().getAttribute("user_id"));
			request.getSession().setAttribute("user_nom", etudiant.getNom());
			request.getSession().setAttribute("user_prenom", etudiant.getPrenom());
			System.out.println(identifiant+etudiant.getNom()+"vient de se connecter en tant que" + compte);
			confirmMessage = "Connexion r��ussie, vous avez bien ��t�� identifi�� comme Etudiant.";
			bienvenue = "Bienvenue "+identifiant;
		}
		else errorMessage = "Connexion échouée";
			
		request.getSession().setAttribute("confirm", confirmMessage);
		request.getSession().setAttribute("error", errorMessage);
		request.getSession().setAttribute("message", bienvenue);
		response.sendRedirect("ficheetudiant"); //Renvoie sur la servlet ficheetudiant
	}

}
