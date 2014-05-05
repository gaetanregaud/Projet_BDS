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
import bds.devweb.model.Sport;


public class AccueilBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AccueilBDSServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Retourne la liste des Sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
		//Retourne la liste es équipes de sport (AS) de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);

		//Retourne la fiche BDS de la personne s'étant connecté avec son identifiant HEI
			String identifiant = (String) request.getSession().getAttribute("user_id");
			BDS bds = Manager.getInstance().getBDS(identifiant);
			request.setAttribute("bds", bds);
		//Ouvre la page acceuilBDS.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/accueilBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
