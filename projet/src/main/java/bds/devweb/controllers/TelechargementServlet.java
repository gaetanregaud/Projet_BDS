package bds.devweb.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bds.devweb.metier.Manager;
import bds.devweb.model.Telechargement;


public class TelechargementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TelechargementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Telechargement> listeEtudiantNote = Manager.getInstance().listerEtudiantNote();
		response.setContentType("text/csv");
		String filename = "note.csv";
	    response.setHeader("Content-Disposition", "attachment; filename=\"note.csv\"");
	    try
	    {
	    	FileWriter writer = new FileWriter(filename);
	        OutputStream outputStream = response.getOutputStream();
	        writer.write("Identifiant, Nom, Prénom, Note de Sport, Nombre de Challenge, NoteFinal\n");
	        writer.write("Identifiant, Nom, Prénom, Note de Sport, Nombre de Challenge, NoteFinale\n");
	        outputStream.flush();
	        outputStream.close();
	    }
	    catch(Exception e)
	    {
	        System.out.println(e.toString());
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
