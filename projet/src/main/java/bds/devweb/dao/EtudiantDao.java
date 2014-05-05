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
						results.getBoolean("certificat_etudiant"),
						results.getString("licence_etudiant"));
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
	
	public List<Etudiant> listerEtudiant(){
		List<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.* FROM etudiant ");
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
						results.getBoolean("certificat_etudiant"),
						results.getString("licence_etudiant"));
				listeEtudiant.add(etudiant);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEtudiant;
	}
	
	public void modifEtudiant(Etudiant etudiant){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("UPDATE etudiant SET nom_etudiant = ?, prenom_etudiant = ?, classe_etudiant = ?, tel_etudiant = ?, mail_etudiant = ?, photo_etudiant = ?, certificat_etudiant = ?, cotisation_etudiant = ?, licence_etudiant = ? WHERE id_etudiant = ?");
			stmt.setString(1,  etudiant.getNom());
			stmt.setString(2,  etudiant.getPrenom());
			stmt.setString(3,  etudiant.getClasse());
			stmt.setString(4,  etudiant.getTelephone());
			stmt.setString(5,  etudiant.getMail());
			stmt.setString(6,  etudiant.getPhoto());
			stmt.setBoolean(7,  etudiant.isCertificat());
			stmt.setBoolean(8,  etudiant.isCotisation());
			stmt.setString(9,  etudiant.getLicence());
			stmt.setString(10,  etudiant.getIdentifiant());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void inscriptionEtudiant(Etudiant etudiant, String password){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO etudiant VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1,  etudiant.getIdentifiant());
			stmt.setString(2,  password);
			stmt.setString(3,  etudiant.getNom());
			stmt.setString(4,  etudiant.getPrenom());
			stmt.setString(5,  etudiant.getTelephone());
			stmt.setString(6,  etudiant.getMail());
			stmt.setString(7,  etudiant.getPhoto());
			stmt.setString(8,  etudiant.getClasse());
			stmt.setBoolean(9,  etudiant.isCotisation());
			stmt.setBoolean(10,  etudiant.isCertificat());
			stmt.setString(11,  etudiant.getLicence());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
				
}
