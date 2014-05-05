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
		// Retourne la liste des sports de la BDD
			List<Sport> listeSports = Manager.getInstance().listerSports();
			request.setAttribute("listeSports", listeSports);
			
		// Retourne la liste des équipes sport de la BDD
			List<EquipeSport> listeEquipeSport = Manager.getInstance().listerEquipeSport();
			request.setAttribute("listeEquipeSport",listeEquipeSport);
			
		//Affiche la page inscription.jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/inscription.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupère le type de la method post à effectuer
			String type = request.getParameter("type");
		
		// Vérifie que l'id étudiant rentrée n'existe pas dans la BDD
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
		
		// Vérifie que l'email rentrée n'existe pas dans la BDD
			if(type.equals("verifEmail")){
				PrintWriter out = response.getWriter();
				String email = request.getParameter("email");
				for(int i=0; i<listeEtudiant.size(); i++){
					if(listeEtudiant.get(i).getMail().equals(email)){
						out.append("oui");
					}	
				}
			}
		
		// Method pour l'inscription d'un nouveau étudiant
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
				Manager.getInstance().inscriptionEtudiant(etudiant, password); //Ajoute l'étudiant à la BDD
				response.sendRedirect("connexion"); //Renvoie sur la servlet connexion
			}
	}

}
