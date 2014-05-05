package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Participer;

public class ParticiperDaoTestCase {
	
	ParticiperDao participerDao = new ParticiperDao();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	private static DateFormat heureFormat = new SimpleDateFormat("HH:mm");
	
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
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00001', 'h00001', 'admine', 'admine', '0611223344', 'admin.admin@hei.fr', 'h00000', 'h00', '0', '1', '123456789')");
		stmt.executeUpdate("INSERT INTO sport VALUES ('s0', 'sport', 'description')");
		stmt.executeUpdate("INSERT INTO categorie VALUES ('c0', 'categorie')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es0', 'AS', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es1', 'AS1', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.executeUpdate("INSERT INTO adresse VALUES ('1', 'adresse', '00', 'rue', 'codep', 'ville', 'pays')");
		stmt.executeUpdate("INSERT INTO challenge VALUES ('ch0', 'challenge', '2014-01-01', '18:00:00', 'description', '1')");
		stmt.executeUpdate("INSERT INTO participer VALUES ('h00000', 'ch0', '1')");
		stmt.close();
		connection.close();	
	}
	
	@Test
	public void test_listerParticipationforEtudiant(){
		List<Participer> listeParticipationforEtudiant = participerDao.listerParticipationforEtudiant("h00000");
		String date1 = "2014-01-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		String heure1 = "18:00:00";
		Date heure2 = null;
		try {
			heure2 = heureFormat.parse(heure1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Assert.assertEquals(1, listeParticipationforEtudiant.size());
		Assert.assertEquals("h00000", listeParticipationforEtudiant.get(0).getId_etudiant());
		Assert.assertEquals("ch0", listeParticipationforEtudiant.get(0).getId_challenge());
		Assert.assertEquals("1", listeParticipationforEtudiant.get(0).getPresence());
		Assert.assertEquals("h00000", listeParticipationforEtudiant.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listeParticipationforEtudiant.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listeParticipationforEtudiant.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("h00", listeParticipationforEtudiant.get(0).getEtudiant().getClasse());
		Assert.assertEquals("0611223344", listeParticipationforEtudiant.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeParticipationforEtudiant.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listeParticipationforEtudiant.get(0).getEtudiant().getPhoto());
		Assert.assertEquals(true, listeParticipationforEtudiant.get(0).getEtudiant().isCertificat());
		Assert.assertEquals(false, listeParticipationforEtudiant.get(0).getEtudiant().isCotisation());
		Assert.assertEquals("123456789", listeParticipationforEtudiant.get(0).getEtudiant().getLicence());
		Assert.assertEquals("ch0", listeParticipationforEtudiant.get(0).getChallenge().getId_challenge());
		Assert.assertEquals("challenge", listeParticipationforEtudiant.get(0).getChallenge().getNom_challenge());
		Assert.assertEquals(date2, listeParticipationforEtudiant.get(0).getChallenge().getDate_challenge());
		Assert.assertEquals(heure2, listeParticipationforEtudiant.get(0).getChallenge().getHeure_challenge());
		Assert.assertEquals("description", listeParticipationforEtudiant.get(0).getChallenge().getDescription_challenge());
		Assert.assertEquals("1", listeParticipationforEtudiant.get(0).getChallenge().getId_adrChal());
		Assert.assertEquals("1", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getId());
		Assert.assertEquals("adresse", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getNom());
		Assert.assertEquals("00", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getNum());
		Assert.assertEquals("rue", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getRue());
		Assert.assertEquals("codep", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getCp());
		Assert.assertEquals("ville", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getVille());
		Assert.assertEquals("pays", listeParticipationforEtudiant.get(0).getChallenge().getAdresse().getPays());

	}
	
	@Test
	public void test_listerParticipationByChallenge(){
		List<Participer> listeParticipationByChallenge = participerDao.listerParticipationByChallenge("ch0");
		String date1 = "2014-01-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		String heure1 = "18:00:00";
		Date heure2 = null;
		try {
			heure2 = heureFormat.parse(heure1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Assert.assertEquals(1, listeParticipationByChallenge.size());
		Assert.assertEquals("h00000", listeParticipationByChallenge.get(0).getId_etudiant());
		Assert.assertEquals("ch0", listeParticipationByChallenge.get(0).getId_challenge());
		Assert.assertEquals("1", listeParticipationByChallenge.get(0).getPresence());
		Assert.assertEquals("h00000", listeParticipationByChallenge.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listeParticipationByChallenge.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listeParticipationByChallenge.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("h00", listeParticipationByChallenge.get(0).getEtudiant().getClasse());
		Assert.assertEquals("0611223344", listeParticipationByChallenge.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeParticipationByChallenge.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listeParticipationByChallenge.get(0).getEtudiant().getPhoto());
		Assert.assertEquals(true, listeParticipationByChallenge.get(0).getEtudiant().isCertificat());
		Assert.assertEquals(false, listeParticipationByChallenge.get(0).getEtudiant().isCotisation());
		Assert.assertEquals("123456789", listeParticipationByChallenge.get(0).getEtudiant().getLicence());
		Assert.assertEquals("ch0", listeParticipationByChallenge.get(0).getChallenge().getId_challenge());
		Assert.assertEquals("challenge", listeParticipationByChallenge.get(0).getChallenge().getNom_challenge());
		Assert.assertEquals(date2, listeParticipationByChallenge.get(0).getChallenge().getDate_challenge());
		Assert.assertEquals(heure2, listeParticipationByChallenge.get(0).getChallenge().getHeure_challenge());
		Assert.assertEquals("description", listeParticipationByChallenge.get(0).getChallenge().getDescription_challenge());
		Assert.assertEquals("1", listeParticipationByChallenge.get(0).getChallenge().getId_adrChal());
		Assert.assertEquals("1", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getId());
		Assert.assertEquals("adresse", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getNom());
		Assert.assertEquals("00", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getNum());
		Assert.assertEquals("rue", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getRue());
		Assert.assertEquals("codep", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getCp());
		Assert.assertEquals("ville", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getVille());
		Assert.assertEquals("pays", listeParticipationByChallenge.get(0).getChallenge().getAdresse().getPays());

	}
	
	@Test
	public void test_listerParticipation(){
		List<Participer> listeParticipation = participerDao.listerParticipation();
		String date1 = "2014-01-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		String heure1 = "18:00:00";
		Date heure2 = null;
		try {
			heure2 = heureFormat.parse(heure1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Assert.assertEquals(1, listeParticipation.size());
		Assert.assertEquals("h00000", listeParticipation.get(0).getId_etudiant());
		Assert.assertEquals("ch0", listeParticipation.get(0).getId_challenge());
		Assert.assertEquals("1", listeParticipation.get(0).getPresence());
		Assert.assertEquals("h00000", listeParticipation.get(0).getEtudiant().getIdentifiant());
		Assert.assertEquals("admin", listeParticipation.get(0).getEtudiant().getNom());
		Assert.assertEquals("admin", listeParticipation.get(0).getEtudiant().getPrenom());
		Assert.assertEquals("h00", listeParticipation.get(0).getEtudiant().getClasse());
		Assert.assertEquals("0611223344", listeParticipation.get(0).getEtudiant().getTelephone());
		Assert.assertEquals("admin.admin@hei.fr", listeParticipation.get(0).getEtudiant().getMail());
		Assert.assertEquals("h00000", listeParticipation.get(0).getEtudiant().getPhoto());
		Assert.assertEquals(true, listeParticipation.get(0).getEtudiant().isCertificat());
		Assert.assertEquals(false, listeParticipation.get(0).getEtudiant().isCotisation());
		Assert.assertEquals("123456789", listeParticipation.get(0).getEtudiant().getLicence());
		Assert.assertEquals("ch0", listeParticipation.get(0).getChallenge().getId_challenge());
		Assert.assertEquals("challenge", listeParticipation.get(0).getChallenge().getNom_challenge());
		Assert.assertEquals(date2, listeParticipation.get(0).getChallenge().getDate_challenge());
		Assert.assertEquals(heure2, listeParticipation.get(0).getChallenge().getHeure_challenge());
		Assert.assertEquals("description", listeParticipation.get(0).getChallenge().getDescription_challenge());
		Assert.assertEquals("1", listeParticipation.get(0).getChallenge().getId_adrChal());
		Assert.assertEquals("1", listeParticipation.get(0).getChallenge().getAdresse().getId());
		Assert.assertEquals("adresse", listeParticipation.get(0).getChallenge().getAdresse().getNom());
		Assert.assertEquals("00", listeParticipation.get(0).getChallenge().getAdresse().getNum());
		Assert.assertEquals("rue", listeParticipation.get(0).getChallenge().getAdresse().getRue());
		Assert.assertEquals("codep", listeParticipation.get(0).getChallenge().getAdresse().getCp());
		Assert.assertEquals("ville", listeParticipation.get(0).getChallenge().getAdresse().getVille());
		Assert.assertEquals("pays", listeParticipation.get(0).getChallenge().getAdresse().getPays());
	}
	
	@Test
	public void test_ajouterParticipationToChallenge(){
		Participer participer =new Participer("h00001", "ch0", "0");
		participerDao.ajouterParticipationToChallenge(participer);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM participer WHERE id_etudiant = 'h00001'");
			result.next();
			Assert.assertEquals("h00001", result.getString("id_etudiant"));
			Assert.assertEquals("ch0", result.getString("id_challenge"));
			Assert.assertEquals("0", result.getString("presence"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_modifierPresence(){
		Participer participer =new Participer("h00000", "ch0", "0");
		participerDao.modifierPresence(participer);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM participer WHERE id_etudiant = 'h00000'");
			result.next();
			Assert.assertEquals("h00000", result.getString("id_etudiant"));
			Assert.assertEquals("ch0", result.getString("id_challenge"));
			Assert.assertEquals("0", result.getString("presence"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
