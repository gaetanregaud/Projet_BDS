package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Etudiant;



public class EtudiantDao {
	
	public boolean seConnecter(String identifiant, String password){
		boolean existe = false;
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM etudiant");
			
			while(results.next() & !existe){
				if(identifiant.equals(results.getString("id_etudiant"))){
					if(password.equals(results.getString("mpd_etudiant"))){
						existe = true;
					}
				}
			}
			results.close();
			stmt.close();
			connection.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean EtudiantExist(String identifiant){
		boolean existe = false;
		
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM etudiant");
			
			while(results.next() & !existe){
				if(identifiant.equals(results.getString("id_etudiant"))){
					existe = true;
				}
			}
			results.close();
			stmt.close();
			connection.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return existe;
	}
				
	public Etudiant getEtudiant(String identifiant){
		Etudiant etudiant = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiant WHERE id_etudiant = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				etudiant = new Etudiant(
						results.getString("id_etudiant"),
						results.getString("nom_etudiant"),
						results.getString("prenom_etudiant"),
						results.getString("classe_etudiant"),
						results.getString("tel_etudiant"),
						results.getString("mail_etudiant"),
						results.getString("photo_etudiant"),
						results.getBoolean("cotisation_etudiant"),
						results.getBoolean("certificat_etudiant"));
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return etudiant;
	}
	
	public List<Etudiant> listerEtudiantByVP(String identifiant){
		List<Etudiant> listeEtudiantByVP = new ArrayList<Etudiant>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.* FROM etudiant INNER JOIN (pratiquer INNER JOIN vp ON pratiquer.id_equipeSport = vp.id_equipeSport) ON etudiant.id_etudiant = pratiquer.id_etudiant WHERE vp.id_etudiant = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Etudiant etudiant = new Etudiant(
						results.getString("id_etudiant"),
						results.getString("nom_etudiant"),
						results.getString("prenom_etudiant"),
						results.getString("classe_etudiant"),
						results.getString("tel_etudiant"),
						results.getString("mail_etudiant"),
						results.getString("photo_etudiant"),
						results.getBoolean("cotisation_etudiant"),
						results.getBoolean("certificat_etudiant"));
				listeEtudiantByVP.add(etudiant);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEtudiantByVP;
	}
	
	public List<Etudiant> listerEtudiantNotOK(){
		List<Etudiant> listeEtudiantNotOK = new ArrayList<Etudiant>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM etudiant WHERE cotisation_etudiant != '1' OR certificat_etudiant != '1'");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Etudiant etudiant = new Etudiant(
						results.getString("id_etudiant"),
						results.getString("nom_etudiant"),
						results.getString("prenom_etudiant"),
						results.getString("classe_etudiant"),
						results.getString("tel_etudiant"),
						results.getString("mail_etudiant"),
						results.getString("photo_etudiant"),
						results.getBoolean("cotisation_etudiant"),
						results.getBoolean("certificat_etudiant"));
				listeEtudiantNotOK.add(etudiant);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEtudiantNotOK;
	}
	
	public List<Etudiant> listerID_Etudiant(String identifiant){
		List<Etudiant> listeID_Etudiant = new ArrayList<Etudiant>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.* FROM etudiant WHERE etudiant.id_etudiant LIKE ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Etudiant etudiant = new Etudiant(
						results.getString("id_etudiant"),
						results.getString("nom_etudiant"),
						results.getString("prenom_etudiant"),
						results.getString("classe_etudiant"),
						results.getString("tel_etudiant"),
						results.getString("mail_etudiant"),
						results.getString("photo_etudiant"),
						results.getBoolean("cotisation_etudiant"),
						results.getBoolean("certificat_etudiant"));
				listeID_Etudiant.add(etudiant);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeID_Etudiant;
	}
				
}
