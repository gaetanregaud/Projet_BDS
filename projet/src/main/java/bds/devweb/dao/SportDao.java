package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Sport;

public class SportDao {
	
	public List<Sport> listerSports (){
		List<Sport> listeSport = new ArrayList<Sport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sport");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Sport sport = new Sport(
						results.getString("id_sport"),
						results.getString("nom_sport"));
					listeSport.add(sport);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeSport;
	}	

	public void ajouterSport (Sport sport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO sport (id_sport, nom_sport, description_sport) VALUES (?, ?, '')");
			stmt.setString(1,  sport.getId_sport());
			stmt.setString(2,  sport.getNom_sport());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void supprimerSport (String id_Sport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			List<String> listeIdEqupieSport = new ArrayList<String>();
			PreparedStatement stmt0 = connection.prepareStatement("SELECT id_equipeSport FROM equipe_sport WHERE id_sport = ?");
			stmt0.setString(1, id_Sport);
			ResultSet results = stmt0.executeQuery();
			while(results.next()){
				String id_equipeSport = results.getString("id_equipeSport");
				listeIdEqupieSport.add(id_equipeSport);
			}
			results.close();
			stmt0.close();
			
			for(int i=0; i<listeIdEqupieSport.size(); i++){
			PreparedStatement stmt1 = connection.prepareStatement("DELETE FROM vp WHERE id_equipeSport = ?");
			stmt1.setString(1,  listeIdEqupieSport.get(i));
			stmt1.executeUpdate();
			stmt1.close();
			}
			
			PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM sport WHERE id_sport = ?");
			stmt2.setString(1,  id_Sport);
			stmt2.executeUpdate();
			stmt2.close();
			
			PreparedStatement stmt3 = connection.prepareStatement("DELETE FROM equipe_sport WHERE id_sport = ?");
			stmt3.setString(1,  id_Sport);
			stmt3.executeUpdate();
			stmt3.close();
			
			//Fermer la connexion
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

}
