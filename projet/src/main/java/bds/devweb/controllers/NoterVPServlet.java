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
import bds.devweb.model.Pratiquer;



public class NoterVPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoterVPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = (String) request.getSession().getAttribute("user_id");
		Etudiant etudiant = Manager.getInstance().getEtudiant(identifiant);
		request.setAttribute("etudiant", etudiant);
		List<Pratiquer> pratiquers = Manager.getInstance().listerPratiquerforEtudiant(identifiant);
		request.setAttribute("pratiquers", pratiquers);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/noterVP.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
