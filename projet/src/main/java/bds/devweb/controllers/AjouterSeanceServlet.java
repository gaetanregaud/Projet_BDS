package bds.devweb.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Pratiquer;
import bds.devweb.model.Seance;


public class AjouterSeanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

    public AjouterSeanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_equipeSport = (String) request.getSession().getAttribute("user_equipeSport");
		String id_seance = request.getParameter("numseance");
		String datejsp = request.getParameter("date");
		List<Pratiquer> pratiquantnote = Manager.getInstance().listerPratiquantNotebyVP(id_equipeSport);
		Date date = null;
		try {
			date = dateFormat.parse(datejsp);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		for(int i = 0; i < pratiquantnote.size();i++){
			String id = pratiquantnote.get(i).getId_etudiant();
			String presence = request.getParameter("presence_"+id);
			System.out.println("Etudiant" + id + presence);
			Seance seance = new Seance(id_seance, id, id_equipeSport, date, presence);
			Manager.getInstance().ajouterSeance(seance);
		}
		response.sendRedirect("fichevp");
	}
}
