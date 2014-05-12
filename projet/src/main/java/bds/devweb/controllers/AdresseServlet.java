package bds.devweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bds.devweb.metier.Manager;
import bds.devweb.model.Adresse;


public class AdresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdresseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String num = request.getParameter("num");
		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		System.out.println(ville);
		Adresse adresse = new Adresse("", nom, num, rue, cp, ville, pays);
		Manager.getInstance().ajouterAdresse(adresse); //Ajoute l'adresse dans la BDD
		adresse = Manager.getInstance().getAdresseByNom(nom); //Récupère l'id de l'adresse enregistré à l'instant
		Gson gson = new Gson();
		String adresseJson = gson.toJson(adresse);
		response.setContentType("application/json");
		response.getWriter().write(adresseJson); //Retourne en JSON la fiche de l'adresse pour le JS
	}

}
