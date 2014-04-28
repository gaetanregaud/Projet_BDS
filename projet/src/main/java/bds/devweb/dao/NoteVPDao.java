package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.NoteVP;

public class NoteVPDao {
	
	public void ajouterNoteVP(NoteVP notevp){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO noteVP (id_etudiant, id_vp, id_equipeSport, note1, note2, note3, note4, note5, note6, note7, note8, note9, note10, noteFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1,  notevp.getId_etudiant());
			stmt.setString(2,  notevp.getId_vp());
			stmt.setString(3,  notevp.getId_equipeSport());
			stmt.setFloat(4,  notevp.getNote1());
			stmt.setFloat(5,  notevp.getNote2());
			stmt.setFloat(6,  notevp.getNote3());
			stmt.setFloat(7,  notevp.getNote4());
			stmt.setFloat(8,  notevp.getNote5());
			stmt.setFloat(9,  notevp.getNote6());
			stmt.setFloat(10,  notevp.getNote7());
			stmt.setFloat(11,  notevp.getNote8());
			stmt.setFloat(12,  notevp.getNote9());
			stmt.setFloat(13,  notevp.getNote10());
			stmt.setFloat(14,  notevp.getResultat());
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<NoteVP> listerNoteVP(String id_vp, String id_equipeSport){
		List<NoteVP> listeNoteVP = new ArrayList<NoteVP>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM noteVP WHERE id_vp = ? AND id_equipeSport = ?");
			stmt.setString(1, id_vp);
			stmt.setString(2, id_equipeSport);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				NoteVP notevp = new NoteVP(
						results.getString("id_etudiant"),
						results.getString("id_vp"),
						results.getString("id_equipeSport"),
						results.getFloat("note1"),
						results.getFloat("note2"),
						results.getFloat("note3"),
						results.getFloat("note4"),
						results.getFloat("note5"),
						results.getFloat("note6"),
						results.getFloat("note7"),
						results.getFloat("note8"),
						results.getFloat("note9"),
						results.getFloat("note10"),
						results.getFloat("noteFinal"));
				listeNoteVP.add(notevp);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeNoteVP;
	}

}
