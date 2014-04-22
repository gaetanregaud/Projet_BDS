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
import bds.devweb.model.Sport;


public class AccueilBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AccueilBDSServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
					List<Sport> lisports = Manager.getInstance().listerLiSports();
					request.setAttribute("lisports", lisports);
				// Fin Listes de la barre de menu du header
				//Donn��es relatives �� la page
					String identifiant = (String) request.getSession().getAttribute("user_id");
					BDS bds = Manager.getInstance().getBDS(identifiant);
					request.setAttribute("bds", bds);
				//Fin Donn��es relatives �� la page
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/accueilBDS.jsp");
					view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
