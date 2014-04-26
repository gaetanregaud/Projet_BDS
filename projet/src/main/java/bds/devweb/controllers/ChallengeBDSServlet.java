package bds.devweb.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bds.devweb.metier.Manager;
import bds.devweb.model.Adresse;
import bds.devweb.model.BDS;
import bds.devweb.model.Challenge;
import bds.devweb.model.Participer;
import bds.devweb.model.Sport;


public class ChallengeBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	private static DateFormat heureFormat = new SimpleDateFormat("hh:mm"); 
	
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
			
			String id_challenge = request.getParameter("id_challenge");
			Challenge challenge = Manager.getInstance().getChallenge(id_challenge);
			Gson gson = new Gson();
			String challengeJson = gson.toJson(challenge);
			response.setContentType("application/json");
			response.getWriter().write(challengeJson);
			
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/challengeBDS.jsp");
			view.forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("newChallenge")){
			String id_challenge = request.getParameter("id_challenge");
			String nom_challenge = request.getParameter("nom_challenge");
			String date = request.getParameter("date_challenge");
			Date date_challenge = null;
			try {
				date_challenge = dateFormat.parse(date);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String heure = request.getParameter("heure_challenge");
			Date heure_challenge = null;
			try {
				heure_challenge = heureFormat.parse(heure);
			}
			catch (ParseException e){
				e.printStackTrace();
			}
			String description_challenge = request.getParameter("description");
			String id_adresse = request.getParameter("lieu");
			System.out.print(id_adresse);
			Challenge challenge = new Challenge(id_challenge, nom_challenge, date_challenge, heure_challenge, description_challenge, id_adresse);
			System.out.print(challenge);
			Manager.getInstance().ajouterChallenge(challenge);
			response.sendRedirect("challengebds");
		}
		if(type.equals("suprChallenge")){
			String id_challenge = request.getParameter("id_challenge");
			Manager.getInstance().deleteChallenge(id_challenge);
			response.sendRedirect("challengebds");
		}
		if(type.equals("modifChallenge")){
			String id_challenge = request.getParameter("id_challenge");
			Challenge challenge = Manager.getInstance().getChallenge(id_challenge);
			Gson gson = new Gson();
			String challengeJson = gson.toJson(challenge);
			response.setContentType("application/json");
			response.getWriter().write(challengeJson);
			System.out.println("Ca marche");
		}
	}

}
