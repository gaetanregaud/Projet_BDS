package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Etudiant;


public class EtudiantDaoTestCase {
	
	private EtudiantDao etudiantDao = new EtudiantDao();
	
	@Before
	public void purgeBDD() throws Exception {
		Connection connection = DataSourceProvider.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM participer");
		stmt.executeUpdate("DELETE FROM pratiquer");
		stmt.executeUpdate("DELETE FROM seance");
		stmt.executeUpdate("DELETE FROM sport");
		stmt.executeUpdate("DELETE FROM equipe_sport");
		stmt.executeUpdate("DELETE FROM challenge");
		stmt.executeUpdate("DELETE FROM adresse");
		stmt.executeUpdate("DELETE FROM noteEtudiant");
		stmt.executeUpdate("DELETE FROM noteVP");
		stmt.executeUpdate("DELETE FROM categorie");
		stmt.executeUpdate("DELETE FROM bds");
		stmt.executeUpdate("DELETE FROM vp");
		stmt.executeUpdate("DELETE FROM etudiant");
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00000', 'h00000', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00000', 'h00', '0', '1', '123456789', '1234')");
		stmt.close();
		connection.close();
		
	}
	
	@Test
	public void test_getEtudiant() {
		Etudiant etudiant = etudiantDao.getEtudiant("h00000");
		
		Assert.assertEquals("h00000", etudiant.getIdentifiant());
		Assert.assertEquals("admin", etudiant.getNom());
		Assert.assertEquals("admin", etudiant.getPrenom());
		Assert.assertEquals("0611223344", etudiant.getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", etudiant.getMail());
		Assert.assertEquals("h00000", etudiant.getPhoto());
		Assert.assertEquals("h00", etudiant.getClasse());
		Assert.assertEquals(false, etudiant.isCotisation());
		Assert.assertEquals(true, etudiant.isCertificat());
		Assert.assertEquals("123456789", etudiant.getLicence());
		
	}
	
	@Test
	public void test_listerEtudiant() {
		List<Etudiant> listeEtudiant = etudiantDao.listerEtudiant();
		
		Assert.assertEquals(1, listeEtudiant.size());
		Assert.assertEquals("h00000", listeEtudiant.get(0).getIdentifiant());
		Assert.assertEquals("admin", listeEtudiant.get(0).getNom());
		Assert.assertEquals("admin", listeEtudiant.get(0).getPrenom());
		Assert.assertEquals("0611223344", listeEtudiant.get(0).getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeEtudiant.get(0).getMail());
		Assert.assertEquals("h00000", listeEtudiant.get(0).getPhoto());
		Assert.assertEquals("h00", listeEtudiant.get(0).getClasse());
		Assert.assertEquals(false, listeEtudiant.get(0).isCotisation());
		Assert.assertEquals(true, listeEtudiant.get(0).isCertificat());
		Assert.assertEquals("123456789", listeEtudiant.get(0).getLicence());
	}
	
	@Test
	public void test_modifEtudiant(){
		Etudiant etudiant = new Etudiant("h00000", "admine", "admine", "h01", "0611223355", "admine.admine@hei.fr", "h00000", true, false, "123456700");
		etudiantDao.modifEtudiant(etudiant);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM etudiant WHERE id_etudiant = 'h00000'");
			result.next();
			Assert.assertEquals("h00000", result.getString("id_etudiant"));
			Assert.assertEquals("admine", result.getString("nom_etudiant"));
			Assert.assertEquals("admine", result.getString("prenom_etudiant"));
			Assert.assertEquals("0611223355", result.getString("tel_etudiant"));
			Assert.assertEquals("admine.admine@hei.fr", result.getString("mail_etudiant"));
			Assert.assertEquals("h00000", result.getString("photo_etudiant"));
			Assert.assertEquals("h01", result.getString("classe_etudiant"));
			Assert.assertEquals(true, result.getBoolean("cotisation_etudiant"));
			Assert.assertEquals(false, result.getBoolean("certificat_etudiant"));
			Assert.assertEquals("123456700", result.getString("licence_etudiant"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_inscriptionEtudiant(){
		Etudiant etudiant = new Etudiant("h00001", "admin", "admin", "h00", "0611223344", "admin.admin@hei.fr", "h00001", false, true, "123456789");
		etudiantDao.inscriptionEtudiant(etudiant, "h00001");
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM etudiant WHERE id_etudiant = 'h00001'");
			result.next();
			Assert.assertEquals("h00001", result.getString("id_etudiant"));
			Assert.assertEquals("admin", result.getString("nom_etudiant"));
			Assert.assertEquals("admin", result.getString("prenom_etudiant"));
			Assert.assertEquals("0611223344", result.getString("tel_etudiant"));
			Assert.assertEquals("admin.admin@hei.fr", result.getString("mail_etudiant"));
			Assert.assertEquals("h00001", result.getString("photo_etudiant"));
			Assert.assertEquals("h00", result.getString("classe_etudiant"));
			Assert.assertEquals(false, result.getBoolean("cotisation_etudiant"));
			Assert.assertEquals(true, result.getBoolean("certificat_etudiant"));
			Assert.assertEquals("123456789", result.getString("licence_etudiant"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
