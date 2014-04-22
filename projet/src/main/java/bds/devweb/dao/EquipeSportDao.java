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
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.* FROM equipe_sport INNER JOIN sport ON sport.id_sport = equipe_sport.id_sport WHERE id_sport = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				EquipeSport equipesport = new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("id_equipeSport"),
						results.getString("nom_equipeSport"));
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
						results.getString("nom_equipeSport"));
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
	

}
