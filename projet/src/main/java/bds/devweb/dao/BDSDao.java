package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bds.devweb.model.BDS;

public class BDSDao {
	
	public boolean seConnecterBDS(String identifiant, String password){
		boolean existe = false;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT bds.id_etudiant, etudiant.mpd_etudiant FROM bds INNER JOIN etudiant ON bds.id_etudiant = etudiant.id_etudiant");
			
			while(results.next() & !existe){
				if(identifiant.equals(results.getString("bds.id_etudiant"))){
					if(password.equals(results.getString("etudiant.mpd_etudiant"))){
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
	
	public BDS getBDS(String identifiant){
		BDS bds = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT bds.* FROM bds WHERE bds.id_etudiant = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				bds = new BDS(
						results.getString("bds.id_etudiant"),
						results.getString("bds.nom_resp"));
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
		return bds;
	}

}
