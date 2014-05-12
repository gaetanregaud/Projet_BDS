package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.NoteVP;

public class NoteVPDaoTestCase {
	
	NoteVPDao notevpDao = new NoteVPDao();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM categorie");
		stmt.executeUpdate("DELETE FROM participer");
		stmt.executeUpdate("DELETE FROM pratiquer");
		stmt.executeUpdate("DELETE FROM seance");
		stmt.executeUpdate("DELETE FROM sport");
		stmt.executeUpdate("DELETE FROM equipe_sport");
		stmt.executeUpdate("DELETE FROM challenge");
		stmt.executeUpdate("DELETE FROM adresse");
		stmt.executeUpdate("DELETE FROM noteEtudiant");
		stmt.executeUpdate("DELETE FROM noteVP");
		stmt.executeUpdate("DELETE FROM bds");
		stmt.executeUpdate("DELETE FROM vp");
		stmt.executeUpdate("DELETE FROM etudiant");
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00000', 'h00000', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00000', 'h00', '0', '1', '123456789', '1234')");
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00001', 'h00001', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00000', 'h00', '0', '1', '123456789', '1234')");
		stmt.executeUpdate("INSERT INTO sport VALUES ('s0', 'sport', 'description')");
		stmt.executeUpdate("INSERT INTO categorie VALUES ('c0', 'categorie')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es0', 'AS', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.executeUpdate("INSERT INTO pratiquer VALUES ('h00000', 'es0', '1')");
		stmt.executeUpdate("INSERT INTO seance VALUES (1, 'h00000', 'es0', '2014-01-01', '1')");
		stmt.executeUpdate("INSERT INTO noteVP VALUES ('h00000', 'h00000', 'es0', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
	}
	
	@Test
	public void test_ajouterNoteVP(){
		NoteVP notevp = new NoteVP("h00001", "h00000", "es0", 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 10);
		notevpDao.ajouterNoteVP(notevp);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM noteVP WHERE id_etudiant = 'h00001'");
			result.next();
			Assert.assertEquals("h00001", result.getString("id_etudiant"));
			Assert.assertEquals("h00000", result.getString("id_vp"));
			Assert.assertEquals("es0", result.getString("id_equipeSport"));
			Assert.assertEquals(1, result.getFloat("note1"),2);
			Assert.assertEquals(1, result.getFloat("note2"),2);
			Assert.assertEquals(1, result.getFloat("note3"),2);
			Assert.assertEquals(1, result.getFloat("note4"),2);
			Assert.assertEquals(1, result.getFloat("note5"),2);
			Assert.assertEquals(1, result.getFloat("note6"),2);
			Assert.assertEquals(1, result.getFloat("note7"),2);
			Assert.assertEquals(1, result.getFloat("note8"),2);
			Assert.assertEquals(1, result.getFloat("note9"),2);
			Assert.assertEquals(1, result.getFloat("note10"),2);
			Assert.assertEquals(10, result.getFloat("noteFinal"),2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_listerNoteVP(){
		List<NoteVP> listeNoteVP = notevpDao.listerNoteVP("h00000", "es0");
		Assert.assertEquals(1, listeNoteVP.size());
		Assert.assertEquals("h00000", listeNoteVP.get(0).getId_etudiant());
		Assert.assertEquals("h00000", listeNoteVP.get(0).getId_vp());
		Assert.assertEquals("es0", listeNoteVP.get(0).getId_equipeSport());
		Assert.assertEquals(0, listeNoteVP.get(0).getNote1(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote2(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote3(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote4(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote5(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote6(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote7(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote8(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote9(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getNote10(),2);
		Assert.assertEquals(0, listeNoteVP.get(0).getResultat(),2);
	}

}
