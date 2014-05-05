package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Pratiquer;

public class PratiquerDaoTestCase {
	
	PratiquerDao pratiquerDao = new PratiquerDao();
	
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
		stmt.executeUpdate("INSERT INTO sport VALUES ('s0', 'sport', 'description')");
		stmt.executeUpdate("INSERT INTO categorie VALUES ('c0', 'categorie')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es0', 'AS', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es1', 'AS1', 'c0', 'description1', 's0')");
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.executeUpdate("INSERT INTO pratiquer VALUES ('h00000', 'es0', '1')");
		
		stmt.close();
		connection.close();	
	}

	@Test
	public void listerPratiqueforEtudiant(){
		List<Pratiquer> listePratiqueforEtudiant = pratiquerDao.listerPratiqueforEtudiant("h00000");
		Assert.assertEquals(1, listePratiqueforEtudiant.size());
		Assert.assertEquals("h00000", listePratiqueforEtudiant.get(0).getId_etudiant());
		Assert.assertEquals("es0", listePratiqueforEtudiant.get(0).getId_equipeSport());
		Assert.assertEquals("1", listePratiqueforEtudiant.get(0).getNote());
		Assert.assertEquals("h00000", listePratiqueforEtudiant.get(0).getVp().getIdentifiant());
		Assert.assertEquals("admin", listePratiqueforEtudiant.get(0).getVp().getNom());
		Assert.assertEquals("admin", listePratiqueforEtudiant.get(0).getVp().getPrenom());
		Assert.assertEquals("0611223344", listePratiqueforEtudiant.get(0).getVp().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listePratiqueforEtudiant.get(0).getVp().getMail());
		Assert.assertEquals("h00000", listePratiqueforEtudiant.get(0).getVp().getPhoto());
		Assert.assertEquals("h00", listePratiqueforEtudiant.get(0).getVp().getClasse());
		Assert.assertEquals(false, listePratiqueforEtudiant.get(0).getVp().isCotisation());
		Assert.assertEquals(true, listePratiqueforEtudiant.get(0).getVp().isCertificat());
		Assert.assertEquals("123456789", listePratiqueforEtudiant.get(0).getVp().getLicence());
		Assert.assertEquals("s0", listePratiqueforEtudiant.get(0).getEquipesport().getId_sport());
		Assert.assertEquals("sport", listePratiqueforEtudiant.get(0).getEquipesport().getNom_sport());
		Assert.assertEquals("es0", listePratiqueforEtudiant.get(0).getEquipesport().getId_equipeSport());
		Assert.assertEquals("AS", listePratiqueforEtudiant.get(0).getEquipesport().getNom_equipeSport());
		Assert.assertEquals("c0", listePratiqueforEtudiant.get(0).getEquipesport().getId_categoire());
		Assert.assertEquals("description", listePratiqueforEtudiant.get(0).getEquipesport().getDescription_equipeSport());	
		
	}
	
	@Test
	public void listerPratiquantbyVP() {
		List<Pratiquer> listePratiquantbyVP = pratiquerDao.listerPratiquantbyEquipeSport("es0");
		Assert.assertEquals(1, listePratiquantbyVP.size());
		Assert.assertEquals("h00000", listePratiquantbyVP.get(0).getId_etudiant());
		Assert.assertEquals("es0", listePratiquantbyVP.get(0).getId_equipeSport());
		Assert.assertEquals("1", listePratiquantbyVP.get(0).getNote());
		Assert.assertEquals("h00000", listePratiquantbyVP.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listePratiquantbyVP.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listePratiquantbyVP.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("0611223344", listePratiquantbyVP.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listePratiquantbyVP.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listePratiquantbyVP.get(0).getEtudiant().getPhoto());
		Assert.assertEquals("h00", listePratiquantbyVP.get(0).getEtudiant().getClasse());
		Assert.assertEquals(false, listePratiquantbyVP.get(0).getEtudiant().isCotisation());
		Assert.assertEquals(true, listePratiquantbyVP.get(0).getEtudiant().isCertificat());
		Assert.assertEquals("123456789", listePratiquantbyVP.get(0).getEtudiant().getLicence());
	}
	
	@Test
	public void ajouterPratique() {
		Pratiquer pratiquer = new Pratiquer("h00000", "es1", "0");
		pratiquerDao.ajouterPratique(pratiquer);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT* FROM pratiquer WHERE id_etudiant = 'h00000' AND id_equipeSport = 'es1'");
			result.next();
			Assert.assertEquals("h00000", result.getString("id_etudiant"));
			Assert.assertEquals("es1", result.getString("id_equipeSport"));
			Assert.assertEquals("0", result.getString("note"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
