package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Sport;

public class SportDao {
	
	public List<Sport> listerLiSports (){
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

}
