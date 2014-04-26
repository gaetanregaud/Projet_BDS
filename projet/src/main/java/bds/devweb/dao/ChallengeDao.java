package bds.devweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Adresse;
import bds.devweb.model.Challenge;



public class ChallengeDao {
	
	public Challenge getLastChallenge(String nom_challenge){
		Challenge challenge = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT challenge.*, adresse.* FROM challenge INNER JOIN adresse ON challenge.id_adresse = adresse.id_adr WHERE challenge.nom_challenge = ? ORDER BY challenge.date_challenge DESC LIMIT 1");
			stmt.setString(1, nom_challenge);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				challenge = new Challenge(
					results.getString("challenge.id_challenge"),
					results.getString("challenge.nom_challenge"),
					results.getDate("challenge.date_challenge"),
					results.getTime("challenge.heure_challenge"),
					results.getString("challenge.description_challenge"),
					results.getString("challenge.id_adresse"));
				challenge.setAdresse(new Adresse(results.getString("adresse.id_adr"),results.getString("adresse.site_adr"),results.getString("adresse.num_adr"),results.getString("adresse.rue_adr"),results.getString("adresse.cp_adr"),results.getString("adresse.ville_adr"),results.getString("adresse.pays_adr")));
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return challenge;
	}
	public void modifierChallenge(Challenge challenge){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("UPDATE challenge SET date_challenge = ?, heure_challenge = ?, description_challenge = ?, id_adresse = ? WHERE id_challenge = ?");
			stmt.setDate(1, new Date(challenge.getDate_challenge().getTime()));
			stmt.setTime(2, new Time(challenge.getHeure_challenge().getTime()));
			stmt.setString(3, challenge.getDescription_challenge());
			stmt.setString(4, challenge.getId_adrChal());
			stmt.setString(5, challenge.getId_challenge());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public Challenge getChallenge(String id_challenge){
		Challenge challenge = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT challenge.*, adresse.* FROM challenge INNER JOIN adresse ON challenge.id_adresse = adresse.id_adr WHERE challenge.id_challenge = ?");
			stmt.setString(1, id_challenge);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				challenge = new Challenge(
					results.getString("challenge.id_challenge"),
					results.getString("challenge.nom_challenge"),
					results.getDate("challenge.date_challenge"),
					results.getTime("challenge.heure_challenge"),
					results.getString("challenge.description_challenge"),
					results.getString("challenge.id_adresse"));
				challenge.setAdresse(new Adresse(results.getString("adresse.id_adr"),results.getString("adresse.site_adr"),results.getString("adresse.num_adr"),results.getString("adresse.rue_adr"),results.getString("adresse.cp_adr"),results.getString("adresse.ville_adr"),results.getString("adresse.pays_adr")));
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return challenge;
	}
	
	public List<Challenge> listerChallenge(){
		List<Challenge> listeChallenge = new ArrayList<Challenge>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT challenge.*, adresse.* FROM challenge INNER JOIN adresse ON challenge.id_adresse = adresse.id_adr ORDER BY challenge.date_challenge DESC");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Challenge challenge = new Challenge(
						results.getString("challenge.id_challenge"),
						results.getString("challenge.nom_challenge"),
						results.getDate("challenge.date_challenge"),
						results.getTime("challenge.heure_challenge"),
						results.getString("challenge.description_challenge"),
						results.getString("challenge.id_adresse"));
				challenge.setAdresse(new Adresse(results.getString("adresse.id_adr"),results.getString("adresse.site_adr"),results.getString("adresse.num_adr"),results.getString("adresse.rue_adr"),results.getString("adresse.cp_adr"),results.getString("adresse.ville_adr"),results.getString("adresse.pays_adr")));
				listeChallenge.add(challenge);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeChallenge;
	}
	
	public List<Challenge> listerTypeChallenge(){
		List<Challenge> listeTypeChallenge = new ArrayList<Challenge>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT challenge.* FROM challenge GROUP BY challenge.nom_challenge");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Challenge challenge = new Challenge(
						results.getString("challenge.id_challenge"),
						results.getString("challenge.nom_challenge"),
						results.getDate("challenge.date_challenge"),
						results.getTime("challenge.heure_challenge"),
						results.getString("challenge.description_challenge"),
						results.getString("challenge.id_adresse"));
				listeTypeChallenge.add(challenge);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeTypeChallenge;
	}
	
	public void AjouterChallenge (Challenge challenge){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			// Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO challenge (id_challenge, nom_challenge, date_challenge, heure_challenge, description_challenge, id_adresse) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, challenge.getId_challenge());
			stmt.setString(2, challenge.getNom_challenge());
			stmt.setDate(3, new Date(challenge.getDate_challenge().getTime()));
			stmt.setTime(4, new Time(challenge.getHeure_challenge().getTime()));
			stmt.setString(5, challenge.getDescription_challenge());
			stmt.setString(6, challenge.getId_adrChal());
			stmt.executeUpdate();

			// Fermer la connexion
			stmt.close();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteChallenge (String id_challenge){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM challenge WHERE id_challenge = ?");
			stmt.setString(1,  id_challenge);
			stmt.executeUpdate();
			stmt.close();
			
			//Fermer la connexion
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
