package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Etudiant;
import bds.devweb.model.NoteVP;
import bds.devweb.model.Pratiquer;



public class NoterVPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoterVPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupère l'id de connexion de l'étudiant
			String identifiant = (String) request.getSession().getAttribute("user_id");
		
		// Retourne la fiche etudiante de l'id 
			Etudiant etudiant = Manager.getInstance().getEtudiant(identifiant);
			request.setAttribute("etudiant", etudiant);
		
		// Retourne la liste des pratiques de l'étudiant
			List<Pratiquer> pratiquers = Manager.getInstance().listerPratiqueforEtudiant(identifiant);
			request.setAttribute("pratiquers", pratiquers);
		
		// Affiche la page noterVP.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/noterVP.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Method post pour l'ajout de la note du VP noter par l'étudiant
			String id_etudiant = request.getParameter("id_etudiant");
			String id_vp = request.getParameter("id_vp");
			String id_equipeSport = request.getParameter("id_equipeSport");
			float note1 = Float.parseFloat(request.getParameter("note1"));
			float note2 = Float.parseFloat(request.getParameter("note2"));
			float note3 = Float.parseFloat(request.getParameter("note3"));
			float note4 = Float.parseFloat(request.getParameter("note4"));
			float note5 = Float.parseFloat(request.getParameter("note5"));
			float note6 = Float.parseFloat(request.getParameter("note6"));
			float note7 = Float.parseFloat(request.getParameter("note7"));
			float note8 = Float.parseFloat(request.getParameter("note8"));
			float note9 = Float.parseFloat(request.getParameter("note9"));
			float note10 = Float.parseFloat(request.getParameter("note10"));
			float noteResultat = Float.parseFloat(request.getParameter("noteResultat"));
			NoteVP notevp = new NoteVP(id_etudiant, id_vp, id_equipeSport, note1, note2, note3, note4, note5, note6, note7, note8, note9, note10, noteResultat);
			Manager.getInstance().ajouterNoteVP(notevp); //Ajout de la note à la BDD
			List<NoteVP> listeNoteVP = Manager.getInstance().listerNoteVP(id_vp, id_equipeSport); //Liste les notes du VP par les pratiquants etudiants
			float note = 0;
			int nombreNote = listeNoteVP.size();
			for(int i=0; i<nombreNote; i++){
				note = note + listeNoteVP.get(i).getResultat(); //Calcule la somme de toutes les notes du VP par les étuidants
			}
			float moyenne = note / nombreNote; // Fait la moyenne des notes
			Manager.getInstance().modifierNoteVP(id_vp, id_equipeSport, moyenne); //Modifie la note général du VP
	}

}
