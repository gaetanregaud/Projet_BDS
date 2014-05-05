package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Adresse;

public class AdresseDaoTestCase {
	
	AdresseDao adresseDao = new AdresseDao();
	
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
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.executeUpdate("INSERT INTO adresse VALUES ('1', 'adresse', '00', 'rue', 'codep', 'ville', 'pays')");
		stmt.executeUpdate("INSERT INTO challenge VALUES ('ch0', 'challenge', '2014-01-01', '18:00:00', 'description', '1')");
		stmt.close();
		connection.close();	
	}
	
	@Test
	public void test_listerAdresse(){
		List<Adresse> listeAdresse = adresseDao.listerAdresse();
		Assert.assertEquals("1", listeAdresse.get(0).getId());
		Assert.assertEquals("adresse", listeAdresse.get(0).getNom());
		Assert.assertEquals("00", listeAdresse.get(0).getNum());
		Assert.assertEquals("rue", listeAdresse.get(0).getRue());
		Assert.assertEquals("codep", listeAdresse.get(0).getCp());
		Assert.assertEquals("ville", listeAdresse.get(0).getVille());
		Assert.assertEquals("pays", listeAdresse.get(0).getPays());
	}
	
	@Test
	public void test_ajouterAdresse(){
		Adresse adresse = new Adresse("2", "adresse1", "01", "rue1", "code1", "ville1", "pays1");
		adresseDao.ajouterAdresse(adresse);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM adresse WHERE id_adr = '2'");
			result.next();
			Assert.assertEquals("2", result.getString("id_adr"));
			Assert.assertEquals("adresse1", result.getString("site_adr"));
			Assert.assertEquals("01", result.getString("num_adr"));
			Assert.assertEquals("rue1", result.getString("rue_adr"));
			Assert.assertEquals("code1", result.getString("cp_adr"));
			Assert.assertEquals("ville1", result.getString("ville_adr"));
			Assert.assertEquals("pays1", result.getString("pays_adr"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_getAdresseByNom(){
		Adresse adresse = adresseDao.getAdresseByNom("adresse");
		Assert.assertEquals("1", adresse.getId());
		Assert.assertEquals("adresse", adresse.getNom());
		Assert.assertEquals("00", adresse.getNum());
		Assert.assertEquals("rue", adresse.getRue());
		Assert.assertEquals("codep", adresse.getCp());
		Assert.assertEquals("ville", adresse.getVille());
		Assert.assertEquals("pays", adresse.getPays());
	}
}
