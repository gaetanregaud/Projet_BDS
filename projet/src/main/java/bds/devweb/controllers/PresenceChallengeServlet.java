package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Participer;


public class PresenceChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PresenceChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_challenge = request.getParameter("id_challenge");
		System.out.println("Challenge  "+id_challenge);
		List<Participer> participants = Manager.getInstance().listerParticipationByChallenge(id_challenge);
		for(int i = 0; i < participants.size(); i++){
			String id_etudiant =  participants.get(i).getId_etudiant();
			String presence = request.getParameter("presence_"+id_etudiant+id_challenge);
			Manager.getInstance().modifierPresence(id_etudiant, id_challenge, presence);
		}
		response.sendRedirect("challengebds");
		
	}

}
