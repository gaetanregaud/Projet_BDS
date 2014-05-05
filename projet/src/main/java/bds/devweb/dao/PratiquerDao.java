package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Pratiquer;
import bds.devweb.model.VP;


public class PratiquerDao {
	
	public List<Pratiquer> listerPratiqueforEtudiant (String identifiant){
		List<Pratiquer> listePratiqueforEtudiant = new ArrayList<Pratiquer>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT pratiquer.*,vp.*,etudiant.*, equipe_sport.*, sport.* FROM pratiquer INNER JOIN (equipe_sport INNER JOIN sport ON equipe_sport.id_sport = sport.id_sport) ON pratiquer.id_equipeSport = equipe_sport.id_equipeSport INNER JOIN (vp INNER JOIN etudiant ON vp.id_etudiant = etudiant.id_etudiant) ON pratiquer.id_equipeSport = vp.id_equipeSport WHERE pratiquer.id_etudiant = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Pratiquer pratiquer = new Pratiquer(
						results.getString("pratiquer.id_etudiant"),
						results.getString("pratiquer.id_equipeSport"),
						results.getString("pratiquer.note"));
						pratiquer.setVp(new VP(
								results.getString("vp.id_etudiant"),
								results.getString("etudiant.nom_etudiant"),
								results.getString("etudiant.prenom_etudiant"),
								results.getString("etudiant.classe_etudiant"),
								results.getString("etudiant.tel_etudiant"),
								results.getString("etudiant.mail_etudiant"),
								results.getString("etudiant.photo_etudiant"),
								results.getBoolean("etudiant.cotisation_etudiant"),
								results.getBoolean("etudiant.certificat_etudiant"),
								results.getString("licence_etudiant"),
								results.getString("vp.id_equipeSport"),
								results.getFloat("vp.note_vp")
								));
						pratiquer.setEquipesport(new EquipeSport(
								results.getString("sport.id_sport"),
								results.getString("sport.nom_sport"),
								results.getString("equipe_sport.id_equipeSport"),
								results.getString("equipe_sport.nom_equipeSport"),
								results.getString("equipe_sport.id_categorie"),
								results.getString("description_equipeSport")
								));
				listePratiqueforEtudiant.add(pratiquer);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listePratiqueforEtudiant;
	}
	
	public List<Pratiquer> listerPratiquantbyEquipeSport (String id_equipeSport){
		List<Pratiquer> listerPratiquantbyVP = new ArrayList<Pratiquer>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.*, pratiquer.*, vp.* FROM etudiant INNER JOIN (pratiquer INNER JOIN vp ON pratiquer.id_equipeSport = vp.id_equipeSport) ON etudiant.id_etudiant = pratiquer.id_etudiant WHERE vp.id_equipeSport = ? ORDER BY etudiant.nom_etudiant ASC, etudiant.prenom_etudiant ASC");
			stmt.setString(1,  id_equipeSport);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Pratiquer pratiquer = new Pratiquer(
						results.getString("pratiquer.id_etudiant"),
						results.getString("pratiquer.id_equipeSport"),
						results.getString("pratiquer.note"));
						pratiquer.setEtudiant(new Etudiant(
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
				listerPratiquantbyVP.add(pratiquer);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listerPratiquantbyVP;
	}
	
	public void ajouterPratique (Pratiquer pratique){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO pratiquer VALUES (?, ?, ?)");
			stmt.setString(1,  pratique.getId_etudiant());
			stmt.setString(2,  pratique.getId_equipeSport());
			stmt.setString(3,  pratique.getNote());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
				e.printStackTrace();
		}
	}

}
