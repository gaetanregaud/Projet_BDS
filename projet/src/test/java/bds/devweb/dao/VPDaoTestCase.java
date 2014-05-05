package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.VP;

public class VPDaoTestCase {
	
	private VPDao vpDao = new VPDao();
	
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
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00000', 'h00000', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00000', 'h00', '0', '1', '123456789')");
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00001', 'h00001', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00001', 'h00', '0', '1', '123456789')");
		stmt.executeUpdate("INSERT INTO sport VALUES ('s0', 'sport', 'description')");
		stmt.executeUpdate("INSERT INTO categorie VALUES ('c0', 'categorie')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es0', 'AS', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es1', 'AS1', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.close();
		connection.close();	
	}
	
	@Test
	public void test_getVP() {
		
		VP vp = vpDao.getVP("h00000");
		 
		Assert.assertEquals("h00000", vp.getIdentifiant());
		Assert.assertEquals("admin", vp.getNom());
		Assert.assertEquals("admin", vp.getPrenom());
		Assert.assertEquals("0611223344", vp.getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", vp.getMail());
		Assert.assertEquals("h00000", vp.getPhoto());
		Assert.assertEquals("h00", vp.getClasse());
		Assert.assertEquals(false, vp.isCotisation());
		Assert.assertEquals(true, vp.isCertificat());
		Assert.assertEquals("es0", vp.getId_equipeSport());
		Assert.assertEquals("es0", vp.getEquipesport().getId_equipeSport());
		Assert.assertEquals("AS", vp.getEquipesport().getNom_equipeSport());
		Assert.assertEquals("s0", vp.getEquipesport().getId_sport());
		Assert.assertEquals("sport", vp.getEquipesport().getNom_sport());
		
	}
	
	@Test
	public void test_listerVP(){
		List<VP> listeVP = vpDao.listerVP();
		Assert.assertEquals(1, listeVP.size());
		Assert.assertEquals("h00000", listeVP.get(0).getIdentifiant());
		Assert.assertEquals("admin", listeVP.get(0).getNom());
		Assert.assertEquals("admin", listeVP.get(0).getPrenom());
		Assert.assertEquals("0611223344", listeVP.get(0).getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeVP.get(0).getMail());
		Assert.assertEquals("h00000", listeVP.get(0).getPhoto());
		Assert.assertEquals("h00", listeVP.get(0).getClasse());
		Assert.assertEquals(false, listeVP.get(0).isCotisation());
		Assert.assertEquals(true, listeVP.get(0).isCertificat());
		Assert.assertEquals("es0", listeVP.get(0).getId_equipeSport());
		Assert.assertEquals("es0", listeVP.get(0).getEquipesport().getId_equipeSport());
		Assert.assertEquals("AS", listeVP.get(0).getEquipesport().getNom_equipeSport());
		Assert.assertEquals("s0", listeVP.get(0).getEquipesport().getId_sport());
		Assert.assertEquals("sport", listeVP.get(0).getEquipesport().getNom_sport());
		
	}
	
	@Test
	public void test_ajouterVPForNewEquipeSport(){
		vpDao.ajouterVPForNewEquipeSport("h00001", "es1");
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM vp WHERE id_etudiant = 'h00001'");
			result.next();
			Assert.assertEquals("h00001", result.getString("id_etudiant"));
			Assert.assertEquals("es1", result.getString("id_equipeSport"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_modifierVPForExistEquipeSport(){
		vpDao.modifierVPForExistEquipeSport("h00001", "es0");
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM vp WHERE id_etudiant = 'h00001'");
			result.next();
			Assert.assertEquals("h00001", result.getString("id_etudiant"));
			Assert.assertEquals("es0", result.getString("id_equipeSport"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
