package bds.devweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Sport;


public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> listeSports = Manager.getInstance().listerLiSports();
			request.setAttribute("listeSports", listeSports);
			List<EquipeSport> listeEuqipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEuqipeSport",listeEuqipeSport);
			
		//Affichage de la Page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/inscription.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println(type);
		List<Etudiant> listeEtudiant = Manager.getInstance().listerEtudiant();
		if(type.equals("verifIdentifiant")){
			PrintWriter out = response.getWriter();
			String identifiant = request.getParameter("identifiant");
			for(int i=0; i<listeEtudiant.size(); i++){
				if(listeEtudiant.get(i).getIdentifiant().equals(identifiant)){
					out.append("oui");
				}	
			}
		}
		if(type.equals("verifEmail")){
			PrintWriter out = response.getWriter();
			String email = request.getParameter("email");
			for(int i=0; i<listeEtudiant.size(); i++){
				if(listeEtudiant.get(i).getMail().equals(email)){
					out.append("oui");
				}	
			}
		}
		if(type.equals("ajouterEtudiant")){
			System.out.println(type);
			String identifiant = request.getParameter("identifiant");
			System.out.println(identifiant);
			String password = request.getParameter("psswd1");
			System.out.println(password);
			String nom = request.getParameter("nom");
			System.out.println(nom);
			String prenom = request.getParameter("prenom");
			System.out.println(prenom);
			String classe = request.getParameter("classe");
			System.out.println(classe);
			String tel = request.getParameter("tel");
			System.out.println(tel);
			String email = request.getParameter("email");
			System.out.println(email);
			Etudiant etudiant = new Etudiant(identifiant, nom, prenom, classe, tel, email, "", false, false, "");
			Manager.getInstance().inscriptionEtudiant(etudiant, password);
			response.sendRedirect("connexion");
		}
	}

}
