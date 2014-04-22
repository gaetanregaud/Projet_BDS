package bds.devweb.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Challenge;


public class AjouterChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	private static DateFormat heureFormat = new SimpleDateFormat("hh:mm"); 
	
    public AjouterChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
	}

}
