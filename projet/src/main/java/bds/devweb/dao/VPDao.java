package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.EquipeSport;
import bds.devweb.model.VP;

public class VPDao {
	
	public boolean seConnecter(String identifiant, String password){
		boolean existe = false;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT vp.id_etudiant, etudiant.mpd_etudiant FROM vp INNER JOIN etudiant ON vp.id_etudiant = etudiant.id_etudiant");
			
			while(results.next() & !existe){
				if(identifiant.equals(results.getString("vp.id_etudiant"))){
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
	
	public VP getVP(String identifiant){
		VP vp = null;
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.*, vp.*, equipe_sport.*, sport.* FROM etudiant INNER JOIN  (vp INNER JOIN (equipe_sport INNER JOIN sport ON equipe_sport.id_sport = sport.id_sport) ON vp.id_equipeSport = equipe_sport.id_equipeSport) ON etudiant.id_etudiant = vp.id_etudiant WHERE vp.id_etudiant = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			if(results.next()){
				vp = new VP(
						results.getString("vp.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant"),
						results.getString("vp.id_equipeSport"),
						results.getFloat("vp.note_vp"),
						results.getString("vp.annee_vp"));
				vp.setEquipesport(new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("equipe_sport.id_equipeSport"),
						results.getString("equipe_sport.nom_equipeSport")
								));
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
		return vp;
	}
	
	public List<VP> listerVP (){
		List<VP> listeVP = new ArrayList<VP>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.*, etudiant.*, vp.* FROM etudiant INNER JOIN (vp INNER JOIN (equipe_sport INNER JOIN sport ON equipe_sport.id_sport = sport.id_sport) ON vp.id_equipeSport = equipe_sport.id_equipeSport) ON etudiant.id_etudiant = vp.id_etudiant ORDER BY sport.nom_sport ASC, equipe_sport.nom_equipeSport ASC");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				VP vp = new VP(
						results.getString("vp.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getString("etudiant.classe_etudiant"),
						results.getString("etudiant.tel_etudiant"),
						results.getString("etudiant.mail_etudiant"),
						results.getString("etudiant.photo_etudiant"),
						results.getBoolean("etudiant.cotisation_etudiant"),
						results.getBoolean("etudiant.certificat_etudiant"),
						results.getString("vp.id_equipeSport"),
						results.getFloat("vp.note_vp"),
						results.getString("vp.annee_vp"));
						vp.setEquipesport(new EquipeSport(
								results.getString("sport.id_sport"),
								results.getString("sport.nom_sport"),
								results.getString("equipe_sport.id_equipeSport"),
								results.getString("equipe_sport.nom_equipeSport")
								));
						listeVP.add(vp);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeVP;
	}
	

}
