package bds.devweb.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Etudiant;
import bds.devweb.model.Seance;

public class SeanceDao {
	
	public List<Seance> listerNumeroSeancebyEquipeSport (String id_equipeSport){
		List<Seance> listeNumeroSeancebyEquipeSportby = new ArrayList<Seance>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT seance.*, etudiant.* FROM etudiant INNER JOIN (seance INNER JOIN pratiquer ON seance.id_equipeSport = pratiquer.id_equipeSport) ON etudiant.id_etudiant = seance.id_etudiant WHERE seance.id_equipeSport = ?  AND pratiquer.note='1' GROUP BY seance.id_seance ORDER BY seance.id_seance ASC");
			stmt.setString(1,  id_equipeSport);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Seance seance = new Seance(
						results.getString("seance.id_seance"),
						results.getString("seance.id_etudiant"),
						results.getString("seance.id_equipeSport"),
						results.getDate("seance.date"),
						results.getString("seance.presence"));
				seance.setEtudiant(new Etudiant(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant"),
						results.getString("licence_etudiant")
						));
				listeNumeroSeancebyEquipeSportby.add(seance);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeNumeroSeancebyEquipeSportby;
	}
	
	public List<Seance> listerSeancebyNumforEquipeSport (String id_equipeSport){
		List<Seance> listeSeancebyNumforEquipeSport = new ArrayList<Seance>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT seance.*, etudiant.* FROM seance INNER JOIN (pratiquer INNER JOIN etudiant ON pratiquer.id_etudiant = etudiant.id_etudiant) ON seance.id_etudiant = pratiquer.id_etudiant WHERE pratiquer.id_equipeSport = ? AND seance.id_equipeSport = ? AND pratiquer.note = '1' ORDER BY seance.id_seance");
			stmt.setString(1,  id_equipeSport);
			stmt.setString(2,  id_equipeSport);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Seance seance = new Seance(
						results.getString("seance.id_seance"),
						results.getString("seance.id_etudiant"),
						results.getString("seance.id_equipeSport"),
						results.getDate("seance.date"),
						results.getString("seance.presence"));
				seance.setEtudiant(new Etudiant(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant"),
						results.getString("licence_etudiant")
						));
				listeSeancebyNumforEquipeSport.add(seance);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeSeancebyNumforEquipeSport;
	}

	public List<Seance> listerSeancebyIdforEquipeSport (String id_equipeSport){
		List<Seance> listeSeancebyIdforEquipeSport = new ArrayList<Seance>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT seance.*, etudiant.* FROM seance INNER JOIN (pratiquer INNER JOIN etudiant ON pratiquer.id_etudiant = etudiant.id_etudiant) ON seance.id_etudiant = pratiquer.id_etudiant WHERE pratiquer.id_equipeSport = ? AND seance.id_equipeSport = ? AND pratiquer.note = '1' ORDER BY seance.id_etudiant ASC, seance.id_seance ASC");
			stmt.setString(1,  id_equipeSport);
			stmt.setString(2,  id_equipeSport);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Seance seance = new Seance(
						results.getString("seance.id_seance"),
						results.getString("seance.id_etudiant"),
						results.getString("seance.id_equipeSport"),
						results.getDate("seance.date"),
						results.getString("seance.presence"));
				seance.setEtudiant(new Etudiant(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant"),
						results.getString("licence_etudiant")
						));
				listeSeancebyIdforEquipeSport.add(seance);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeSeancebyIdforEquipeSport;
	}
	
	public List<Seance> listerSeancebyIdforEtudiant (String identifiant){
		List<Seance> listerSeancebyIdforEtudiant = new ArrayList<Seance>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT seance.* FROM seance INNER JOIN pratiquer ON seance.id_equipeSport = pratiquer.id_equipeSport WHERE seance.id_etudiant = ? AND pratiquer.note = '1'");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Seance seance = new Seance(
						results.getString("seance.id_seance"),
						results.getString("seance.id_etudiant"),
						results.getString("seance.id_equipeSport"),
						results.getDate("seance.date"),
						results.getString("seance.presence"));
				listerSeancebyIdforEtudiant.add(seance);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listerSeancebyIdforEtudiant;
	}
	
	public void ajouterSeance (Seance seance){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			// Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO seance (id_seance, id_etudiant, id_equipeSport, date, presence) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, seance.getId_seance());
			stmt.setString(2, seance.getId_etudiant());
			stmt.setString(3, seance.getId_equipeSport());
			stmt.setDate(4, new Date(seance.getDate_seance().getTime()));
			stmt.setString(5, seance.getPresence_seance());
			stmt.executeUpdate();

			// Fermer la connexion
			stmt.close();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
