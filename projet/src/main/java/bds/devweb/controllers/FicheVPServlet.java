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
import bds.devweb.model.Pratiquer;
import bds.devweb.model.Seance;
import bds.devweb.model.Sport;
import bds.devweb.model.VP;

public class FicheVPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FicheVPServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
		// Fin Listes de la barre de menu du header
		//Donn��es relatives �� la page
			String identifiant = (String) request.getSession().getAttribute("user_id");
			String id_sport = (String) request.getSession().getAttribute("user_sport");
			String id_equipeSport = (String) request.getSession().getAttribute("user_equipeSport");
			VP vp = Manager.getInstance().getVP(identifiant);
			request.setAttribute("vp", vp);
			List<Pratiquer> pratiquantsnote = Manager.getInstance().listerPratiquantNotebyVP(id_equipeSport);
			request.setAttribute("pratiquantsnote", pratiquantsnote);
			List<Pratiquer> pratiquants = Manager.getInstance().listerPratiquantbyVP(id_equipeSport);
			request.setAttribute("pratiquants", pratiquants);
			List<EquipeSport> equipessport = Manager.getInstance().listerEquipeSportbySport(id_sport);
			request.setAttribute("equipessport", equipessport);
			List<Seance> seancesbynum = Manager.getInstance().listerSeancebyNumforEquipeSport(id_equipeSport);
			request.setAttribute("seancesbynum", seancesbynum);
			List<Seance> seancesbyid = Manager.getInstance().listerSeancebyIdforEquipeSport(id_equipeSport);
			request.setAttribute("seancesbyid", seancesbyid);
			List<Seance> numseances = Manager.getInstance().listerNumeroSeancebyEquipeSport(id_equipeSport);
			request.setAttribute("numseances", numseances);
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/ficheVP.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
