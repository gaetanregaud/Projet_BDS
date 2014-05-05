package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Adresse;

public class AdresseDao {
	
	public List<Adresse> listerAdresse(){
	List<Adresse> listeAdresse = new ArrayList<Adresse>();
	try {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		//Utiliser la connexion
		PreparedStatement stmt = connection.prepareStatement("SELECT adresse.* FROM adresse");
		ResultSet results = stmt.executeQuery();
		while(results.next()){
			Adresse adresse = new Adresse(
					results.getString("adresse.id_adr"),
					results.getString("adresse.site_adr"),
					results.getString("adresse.num_adr"),
					results.getString("adresse.rue_adr"),
					results.getString("adresse.cp_adr"),
					results.getString("adresse.ville_adr"),
					results.getString("adresse.pays_adr"));
					listeAdresse.add(adresse);
		}
		//Fermer la connexion
		results.close();
		stmt.close();
		connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeAdresse;
	}
	
	public void ajouterAdresse (Adresse adresse){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			// Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO adresse  VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, adresse.getId());
			stmt.setString(2, adresse.getNom());
			stmt.setString(3, adresse.getNum());
			stmt.setString(4, adresse.getRue());
			stmt.setString(5, adresse.getCp());
			stmt.setString(6, adresse.getVille());
			stmt.setString(7, adresse.getPays());

			stmt.executeUpdate();

			// Fermer la connexion
			stmt.close();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Adresse getAdresseByNom(String nom_adresse){
		Adresse adresse = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT adresse.* FROM adresse WHERE adresse.site_adr = ?");
			stmt.setString(1, nom_adresse);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				adresse = new Adresse(
					results.getString("adresse.id_adr"),
					results.getString("adresse.site_adr"),
					results.getString("adresse.num_adr"),
					results.getString("adresse.rue_adr"),
					results.getString("adresse.cp_adr"),
					results.getString("adresse.ville_adr"),
					results.getString("adresse.pays_adr"));
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return adresse;
	}

}
