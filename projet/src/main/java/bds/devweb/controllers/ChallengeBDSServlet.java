package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Adresse;
import bds.devweb.model.BDS;
import bds.devweb.model.Challenge;
import bds.devweb.model.Participer;
import bds.devweb.model.Sport;


public class ChallengeBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChallengeBDSServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			List<Challenge> challenges = Manager.getInstance().listerChallenge();
			request.setAttribute("challenges", challenges);
			List<Adresse> adresses = Manager.getInstance().listerAdresse();
			request.setAttribute("adresses", adresses);
			List<Participer> participants = Manager.getInstance().listerParticipation();
			request.setAttribute("participants", participants);
			List<Challenge> typeschallenge = Manager.getInstance().listerTypeChallenge();
			request.setAttribute("typeschallenge", typeschallenge);
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/challengeBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
