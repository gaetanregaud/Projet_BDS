package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Seance;

public class SeanceDaoTestCase {
	
	SeanceDao seanceDao = new SeanceDao();
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

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
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.executeUpdate("INSERT INTO pratiquer VALUES ('h00000', 'es0', '1')");
		stmt.executeUpdate("INSERT INTO seance VALUES (1, 'h00000', 'es0', '2014-01-01', '1')");
	}
	
	@Test
	public void listerNumeroSeancebyEquipeSport(){
		List<Seance> ListeSeancebyEquipeSport = seanceDao.listerNumeroSeancebyEquipeSport("es0");
		Assert.assertEquals(1, ListeSeancebyEquipeSport.size());
		Assert.assertEquals("1", ListeSeancebyEquipeSport.get(0).getId_seance());
		Assert.assertEquals("h00000", ListeSeancebyEquipeSport.get(0).getId_etudiant());
		Assert.assertEquals("es0", ListeSeancebyEquipeSport.get(0).getId_equipeSport());
		Assert.assertEquals(1, ListeSeancebyEquipeSport.get(0).getDate_seance().getDate());
		Assert.assertEquals(0, ListeSeancebyEquipeSport.get(0).getDate_seance().getMonth());
		Assert.assertEquals(114, ListeSeancebyEquipeSport.get(0).getDate_seance().getYear());
		Assert.assertEquals("1", ListeSeancebyEquipeSport.get(0).getPresence_seance());
		Assert.assertEquals("h00000", ListeSeancebyEquipeSport.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", ListeSeancebyEquipeSport.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", ListeSeancebyEquipeSport.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("0611223344", ListeSeancebyEquipeSport.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", ListeSeancebyEquipeSport.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", ListeSeancebyEquipeSport.get(0).getEtudiant().getPhoto());
		Assert.assertEquals("h00", ListeSeancebyEquipeSport.get(0).getEtudiant().getClasse());
		Assert.assertEquals(false, ListeSeancebyEquipeSport.get(0).getEtudiant().isCotisation());
		Assert.assertEquals(true, ListeSeancebyEquipeSport.get(0).getEtudiant().isCertificat());
		Assert.assertEquals("123456789", ListeSeancebyEquipeSport.get(0).getEtudiant().getLicence());
		
	}
	
	@Test
	public void listerSeancebyNumforEquipeSport(){
		List<Seance> listeSeancebyNumforEquipeSport = seanceDao.listerSeancebyNumforEquipeSport("es0");
		Assert.assertEquals(1, listeSeancebyNumforEquipeSport.size());
		Assert.assertEquals("1", listeSeancebyNumforEquipeSport.get(0).getId_seance());
		Assert.assertEquals("h00000", listeSeancebyNumforEquipeSport.get(0).getId_etudiant());
		Assert.assertEquals("es0", listeSeancebyNumforEquipeSport.get(0).getId_equipeSport());
		Assert.assertEquals(1, listeSeancebyNumforEquipeSport.get(0).getDate_seance().getDate());
		Assert.assertEquals(0, listeSeancebyNumforEquipeSport.get(0).getDate_seance().getMonth());
		Assert.assertEquals(114, listeSeancebyNumforEquipeSport.get(0).getDate_seance().getYear());
		Assert.assertEquals("1", listeSeancebyNumforEquipeSport.get(0).getPresence_seance());
		Assert.assertEquals("h00000", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("0611223344", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getPhoto());
		Assert.assertEquals("h00", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getClasse());
		Assert.assertEquals(false, listeSeancebyNumforEquipeSport.get(0).getEtudiant().isCotisation());
		Assert.assertEquals(true, listeSeancebyNumforEquipeSport.get(0).getEtudiant().isCertificat());
		Assert.assertEquals("123456789", listeSeancebyNumforEquipeSport.get(0).getEtudiant().getLicence());
	}
	
	@Test
	public void listerSeancebyIdforEquipeSport(){
		List<Seance> listeSeancebyIdforEquipeSport = seanceDao.listerSeancebyIdforEquipeSport("es0");
		Assert.assertEquals(1, listeSeancebyIdforEquipeSport.size());
		Assert.assertEquals("1", listeSeancebyIdforEquipeSport.get(0).getId_seance());
		Assert.assertEquals("h00000", listeSeancebyIdforEquipeSport.get(0).getId_etudiant());
		Assert.assertEquals("es0", listeSeancebyIdforEquipeSport.get(0).getId_equipeSport());
		Assert.assertEquals(1, listeSeancebyIdforEquipeSport.get(0).getDate_seance().getDate());
		Assert.assertEquals(0, listeSeancebyIdforEquipeSport.get(0).getDate_seance().getMonth());
		Assert.assertEquals(114, listeSeancebyIdforEquipeSport.get(0).getDate_seance().getYear());
		Assert.assertEquals("1", listeSeancebyIdforEquipeSport.get(0).getPresence_seance());
		Assert.assertEquals("h00000", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("0611223344", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getPhoto());
		Assert.assertEquals("h00", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getClasse());
		Assert.assertEquals(false, listeSeancebyIdforEquipeSport.get(0).getEtudiant().isCotisation());
		Assert.assertEquals(true, listeSeancebyIdforEquipeSport.get(0).getEtudiant().isCertificat());
		Assert.assertEquals("123456789", listeSeancebyIdforEquipeSport.get(0).getEtudiant().getLicence());
	}
	
	@Test
	public void listerSeancebyIdforEtudiant(){
		List<Seance> listerSeancebyIdforEtudiant = seanceDao.listerSeancebyIdforEtudiant("h00000");
		Assert.assertEquals(1, listerSeancebyIdforEtudiant.size());
		Assert.assertEquals("1", listerSeancebyIdforEtudiant.get(0).getId_seance());
		Assert.assertEquals("h00000", listerSeancebyIdforEtudiant.get(0).getId_etudiant());
		Assert.assertEquals("es0", listerSeancebyIdforEtudiant.get(0).getId_equipeSport());
		Assert.assertEquals("01 janvier 2014", listerSeancebyIdforEtudiant.get(0).getDateFormatee());
		Assert.assertEquals("1", listerSeancebyIdforEtudiant.get(0).getPresence_seance());
	}
	
	@Test
	public void ajouterSeance(){
		String date1 = "2014-02-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Seance seance = new Seance("2", "h00000", "es0", date2, "0");
		seanceDao.ajouterSeance(seance);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM seance WHERE id_seance = 2");
			result.next();
			Assert.assertEquals("2", result.getString("id_seance"));
			Assert.assertEquals("h00000", result.getString("id_etudiant"));
			Assert.assertEquals("es0", result.getString("id_equipeSport"));
			Assert.assertEquals(1, result.getDate("date").getDate());
			Assert.assertEquals(1, result.getDate("date").getMonth());
			Assert.assertEquals(114, result.getDate("date").getYear());
			Assert.assertEquals("0", result.getString("presence"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
