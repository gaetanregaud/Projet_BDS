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


public class EquipeSportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EquipeSportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retourne la liste des Sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
		
		// Retourne la liste des equipes sports de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			
		//Recupère l'id de connexion et Retourne l'equipe sport et le VP 
			String id_equipeSport = request.getParameter("id");
			System.out.print(id_equipeSport);
			VP equipeSportVp = Manager.getInstance().getVPAndEquipeSport(id_equipeSport);
			request.setAttribute("equipeSportVp", equipeSportVp);
		
		//Affiche la page equipeSport.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/equipeSport.jsp");
			view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
