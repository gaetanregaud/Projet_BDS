package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.EquipeSport;

public class EquipeSportDao {
	
	public List<EquipeSport> listerEquipeSportsbySport (String identifiant){
		List<EquipeSport> listeEquipeSport = new ArrayList<EquipeSport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.* FROM equipe_sport INNER JOIN sport ON sport.id_sport = equipe_sport.id_sport WHERE equipe_sport.id_sport = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				EquipeSport equipesport = new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("id_equipeSport"),
						results.getString("nom_equipeSport"),
						results.getString("equipe_sport.id_categorie"),
						results.getString("description_equipeSport"));
					listeEquipeSport.add(equipesport);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEquipeSport;
	}
	
	public List<EquipeSport> listerEquipeSport (){
		List<EquipeSport> listeEquipeSport = new ArrayList<EquipeSport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.* FROM equipe_sport INNER JOIN sport ON sport.id_sport = equipe_sport.id_sport");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				EquipeSport equipesport = new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("id_equipeSport"),
						results.getString("nom_equipeSport"),
						results.getString("equipe_sport.id_categorie"),
						results.getString("description_equipeSport"));
					listeEquipeSport.add(equipesport);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEquipeSport;
	}
	
	public void supprimerEquipeSport (String id_equipeSport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM equipe_sport WHERE id_equipeSport = ?");
			PreparedStatement stmts = connection.prepareStatement("DELETE FROM vp WHERE id_equipeSport = ?");
			stmt.setString(1,  id_equipeSport);
			stmts.setString(1,  id_equipeSport);
			stmt.executeUpdate();
			stmts.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void ajouterEquipeSport (EquipeSport equipesport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO equipe_sport VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1,  equipesport.getId_equipeSport());
			stmt.setString(2,  equipesport.getNom_equipeSport());
			stmt.setString(3,  equipesport.getId_categoire());
			stmt.setString(4,  equipesport.getDescription_equipeSport());
			stmt.setString(5,  equipesport.getId_sport());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

}
