package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.Telechargement;

public class TelechargementDao {
	
	public List<Telechargement> listerEtudiantNote(){
		List<Telechargement> listeEtudiantNote = new ArrayList<Telechargement>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT etudiant.id_etudiant, etudiant.nom_etudiant, etudiant.prenom_etudiant, noteEtudiant.note, COUNT(*) As nb_chall FROM participer INNER JOIN (etudiant INNER JOIN noteEtudiant ON etudiant.id_etudiant = noteEtudiant.id_etudiant) ON participer.id_etudiant = etudiant.id_etudiant GROUP BY participer.id_etudiant");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				Telechargement etudiantNote = new Telechargement(
						results.getString("etudiant.id_etudiant"),
						results.getString("etudiant.nom_etudiant"),
						results.getString("etudiant.prenom_etudiant"),
						results.getFloat("noteEtudiant.note"),
						results.getInt("nb_chall"));
				listeEtudiantNote.add(etudiantNote);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEtudiantNote;
	}

}
