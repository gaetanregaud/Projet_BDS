package bds.devweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;



public class RechercherIdEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RechercherIdEtudiantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_etudiant = request.getParameter("vp");
		boolean existe = Manager.getInstance().EtudiantExiste(id_etudiant);
		PrintWriter out = response.getWriter();
		if(existe == true){
			out.append("oui");
		}
		else{
			out.append("non");
		}

	}

}
