package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.BDS;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Sport;
import bds.devweb.model.VP;


public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConnexionServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
		// Fin Listes de la barre de menu du header

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/connexion.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String compte = request.getParameter("compte");
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		String confirmMessage ="";
		String errorMessage = "";
		String bienvenue = "";
		
		if(compte.equals("etudiant"))
		{
			if(Manager.getInstance().seConnecterEtudiant(identifiant, password)){
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
			response.sendRedirect("ficheetudiant");
		}
		else if(compte.equals("vp"))
		{
			if(Manager.getInstance().seConnecterVP(identifiant, password)){
				request.getSession().setAttribute("user_type", compte);
				request.getSession().setAttribute("user_id", request.getParameter("identifiant"));
				VP vp = Manager.getInstance().getVP((String) request.getSession().getAttribute("user_id"));
				request.getSession().setAttribute("user_nom", vp.getNom());
				request.getSession().setAttribute("user_prenom", vp.getPrenom());
				request.getSession().setAttribute("user_sport", vp.getEquipesport().getId_sport());
				request.getSession().setAttribute("user_equipeSport", vp.getId_equipeSport());
				System.out.println(identifiant+vp.getNom()+"vient de se connecter en tant que" + compte);
				confirmMessage = "Connexion r��ussie, vous avez bien ��t�� identifi�� comme VP.";
				bienvenue = "Bienvenue VP : "+identifiant;
			}
			else errorMessage = "Connexion ��chou��";		
			
			request.getSession().setAttribute("confirm", confirmMessage);
			request.getSession().setAttribute("error", errorMessage);
			request.getSession().setAttribute("message", bienvenue);
			response.sendRedirect("fichevp");
		}	
		else if(compte.equals("bds"))
		{
			if(Manager.getInstance().seConnecterBDS(identifiant, password)){
				request.getSession().setAttribute("user_type", compte);
				request.getSession().setAttribute("user_id", request.getParameter("identifiant"));
				BDS bds = Manager.getInstance().getBDS((String) request.getSession().getAttribute("user_id"));
				request.getSession().setAttribute("user_nom", bds.getNom_resp());
				System.out.println(identifiant+bds.getNom_resp()+"vient de se connecter en tant que" + compte);
				confirmMessage = "Connexion r��ussie, vous avez bien ��t�� identifi�� comme BDS.";
				bienvenue = "Bienvenue : "+identifiant;
			}
			else errorMessage = "Connexion ��chou��";		
			
			request.getSession().setAttribute("confirm", confirmMessage);
			request.getSession().setAttribute("error", errorMessage);
			request.getSession().setAttribute("message", bienvenue);
			response.sendRedirect("accueilbds");
		}	
	}

}
