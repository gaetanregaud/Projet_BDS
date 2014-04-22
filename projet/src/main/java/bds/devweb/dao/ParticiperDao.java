package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Adresse;
import bds.devweb.model.Challenge;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Participer;



public class ParticiperDao {
	
	public List<Participer> listerParticipationforEtudiant (String identifiant){
		List<Participer> listeParticipationforEtudiant = new ArrayList<Participer>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT participer.*, challenge.*, etudiant.*, adresse.* FROM adresse INNER JOIN (challenge INNER JOIN (participer INNER JOIN etudiant ON participer.id_etudiant = etudiant.id_etudiant)ON participer.id_challenge = challenge.id_challenge) ON adresse.id_adr = challenge.id_adresse WHERE participer.id_etudiant = ? ORDER BY challenge.date_challenge DESC");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Participer participer = new Participer(
						results.getString("participer.id_etudiant"),
						results.getString("participer.id_challenge"),
						results.getString("participer.presence"));
				participer.setEtudiant(new Etudiant(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant")));
				participer.setChallenge(new Challenge(
						results.getString("challenge.id_challenge"),
						results.getString("challenge.nom_challenge"),
						results.getDate("challenge.date_challenge"),
						results.getTime("challenge.heure_challenge"),
						results.getString("challenge.description_challenge"),
						results.getString("challenge.id_adresse")));
				participer.getChallenge().setAdresse(new Adresse(
						results.getString("adresse.id_adr"),
						results.getString("adresse.site_adr"),
						results.getString("adresse.num_adr"),
						results.getString("adresse.rue_adr"),
						results.getString("adresse.cp_adr"),
						results.getString("adresse.ville_adr"),
						results.getString("adresse.pays_adr")
						));
				listeParticipationforEtudiant.add(participer);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeParticipationforEtudiant;
	}
	
	public List<Participer> listerParticipationByChallenge (String id_challenge){
		List<Participer> listeParticipationByChallenge = new ArrayList<Participer>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT participer.*, challenge.*, adresse.* FROM participer INNER JOIN (challenge INNER JOIN adresse ON challenge.id_adresse = adresse.id_adr) ON participer.id_challenge = challenge.id_challenge WHERE participer.id_challenge = ?");
			stmt.setString(1,  id_challenge);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Participer participer = new Participer(
						results.getString("participer.id_etudiant"),
						results.getString("participer.id_challenge"),
						results.getString("participer.presence"));
				participer.setChallenge(new Challenge(
						results.getString("challenge.id_challenge"),
						results.getString("challenge.nom_challenge"),
						results.getDate("challenge.date_challenge"),
						results.getTime("challenge.heure_challenge"),
						results.getString("challenge.description_challenge"),
						results.getString("challenge.id_adresse")));
				participer.getChallenge().setAdresse(new Adresse(
						results.getString("adresse.id_adr"),
						results.getString("adresse.site_adr"),
						results.getString("adresse.num_adr"),
						results.getString("adresse.rue_adr"),
						results.getString("adresse.cp_adr"),
						results.getString("adresse.ville_adr"),
						results.getString("adresse.pays_adr")
						));
				listeParticipationByChallenge.add(participer);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeParticipationByChallenge;
	}
	
	public List<Participer> listerParticipation(){
		List<Participer> listeParticipation = new ArrayList<Participer>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT participer.*, challenge.*, etudiant.*, adresse.* FROM adresse INNER JOIN (challenge  INNER JOIN (participer INNER JOIN etudiant ON participer.id_etudiant = etudiant.id_etudiant) ON participer.id_challenge = challenge.id_challenge) ON adresse.id_adr = challenge.id_adresse");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Participer participer = new Participer(
						results.getString("participer.id_etudiant"),
						results.getString("participer.id_challenge"),
						results.getString("participer.presence"));
				participer.setEtudiant(new Etudiant(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant")));
				participer.getChallenge().setAdresse(new Adresse(
						results.getString("adresse.id_adr"),
						results.getString("adresse.site_adr"),
						results.getString("adresse.num_adr"),
						results.getString("adresse.rue_adr"),
						results.getString("adresse.cp_adr"),
						results.getString("adresse.ville_adr"),
						results.getString("adresse.pays_adr")
						));
				listeParticipation.add(participer);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeParticipation;
	}
	
	public void AjouterParticipationToChallenge (Participer participer){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			// Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO participer (id_etudiant, id_challenge, presence) VALUES (?, ?, ?)");
			stmt.setString(1, participer.getId_etudiant());
			stmt.setString(2, participer.getId_challenge());
			stmt.setString(3, participer.getPresence());
			stmt.executeUpdate();

			// Fermer la connexion
			stmt.close();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void ModifierPresence (String id_etudiant, String id_challenge, String presence){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			// Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("UPDATE participer SET presence = ? WHERE id_etudiant = ? AND id_challenge = ?");
			stmt.setString(1, presence);
			stmt.setString(2, id_etudiant);
			stmt.setString(3, id_challenge);
			stmt.executeUpdate();

			// Fermer la connexion
			stmt.close();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
