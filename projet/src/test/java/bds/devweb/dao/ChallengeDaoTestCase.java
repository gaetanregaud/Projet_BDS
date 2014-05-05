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

import bds.devweb.model.Challenge;

public class ChallengeDaoTestCase {
	
	ChallengeDao challengeDao = new ChallengeDao();
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
	public void test_getLastChallenge(){
		Challenge challenge = challengeDao.getLastChallenge("challenge");
		Assert.assertEquals("ch0", challenge.getId_challenge());
		Assert.assertEquals("challenge", challenge.getNom_challenge());
		Assert.assertEquals("01 janvier 2014", challenge.getDateFormatee());
		Assert.assertEquals("18 00", challenge.getHeureFormatee());
		Assert.assertEquals("description", challenge.getDescription_challenge());
		Assert.assertEquals("1", challenge.getId_adrChal());
		Assert.assertEquals("adresse", challenge.getAdresse().getNom());
		Assert.assertEquals("00", challenge.getAdresse().getNum());
		Assert.assertEquals("rue", challenge.getAdresse().getRue());
		Assert.assertEquals("codep", challenge.getAdresse().getCp());
		Assert.assertEquals("ville", challenge.getAdresse().getVille());
		Assert.assertEquals("pays", challenge.getAdresse().getPays());
		
	}
	
	@Test
	public void test_modifierChallenge(){
		String date1 = "2014-02-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		String heure1 = "06:00:00";
		Date heure2 = null;
		try {
			heure2 = heureFormat.parse(heure1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Challenge challenge = new Challenge("ch0", "challenge", date2, heure2, "description1", "1");
		challengeDao.modifierChallenge(challenge);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM challenge WHERE id_challenge = 'ch0'");
			result.next();
			Assert.assertEquals("ch0", result.getString("id_challenge"));
			Assert.assertEquals("challenge", result.getString("nom_challenge"));
			Assert.assertEquals("2014-02-01", result.getString("date_challenge"));
			Assert.assertEquals("06:00:00", result.getString("heure_challenge"));
			Assert.assertEquals("description1", result.getString("description_challenge"));
			Assert.assertEquals("1", result.getString("id_adresse"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_getChallenge(){
		Challenge challenge = challengeDao.getChallenge("ch0");
		Assert.assertEquals("ch0", challenge.getId_challenge());
		Assert.assertEquals("challenge", challenge.getNom_challenge());
		Assert.assertEquals("01 janvier 2014", challenge.getDateFormatee());
		Assert.assertEquals("18 00", challenge.getHeureFormatee());
		Assert.assertEquals("description", challenge.getDescription_challenge());
		Assert.assertEquals("1", challenge.getId_adrChal());
		Assert.assertEquals("adresse", challenge.getAdresse().getNom());
		Assert.assertEquals("00", challenge.getAdresse().getNum());
		Assert.assertEquals("rue", challenge.getAdresse().getRue());
		Assert.assertEquals("codep", challenge.getAdresse().getCp());
		Assert.assertEquals("ville", challenge.getAdresse().getVille());
		Assert.assertEquals("pays", challenge.getAdresse().getPays());
		
	}
	
	@Test
	public void test_listerChallenge(){
		List<Challenge> listeChallenge = challengeDao.listerChallenge();
		Assert.assertEquals(1, listeChallenge.size());
		Assert.assertEquals("ch0", listeChallenge.get(0).getId_challenge());
		Assert.assertEquals("challenge", listeChallenge.get(0).getNom_challenge());
		Assert.assertEquals("01 janvier 2014", listeChallenge.get(0).getDateFormatee());
		Assert.assertEquals("18 00", listeChallenge.get(0).getHeureFormatee());
		Assert.assertEquals("description", listeChallenge.get(0).getDescription_challenge());
		Assert.assertEquals("1", listeChallenge.get(0).getId_adrChal());
		Assert.assertEquals("adresse", listeChallenge.get(0).getAdresse().getNom());
		Assert.assertEquals("00", listeChallenge.get(0).getAdresse().getNum());
		Assert.assertEquals("rue", listeChallenge.get(0).getAdresse().getRue());
		Assert.assertEquals("codep", listeChallenge.get(0).getAdresse().getCp());
		Assert.assertEquals("ville", listeChallenge.get(0).getAdresse().getVille());
		Assert.assertEquals("pays", listeChallenge.get(0).getAdresse().getPays());
	}
	
	@Test
	public void test_listerTypeChallenge(){
		List<Challenge> listeTypeChallenge = challengeDao.listerTypeChallenge();
		Assert.assertEquals(1, listeTypeChallenge.size());
		Assert.assertEquals("ch0", listeTypeChallenge.get(0).getId_challenge());
		Assert.assertEquals("challenge", listeTypeChallenge.get(0).getNom_challenge());
		Assert.assertEquals("01 janvier 2014", listeTypeChallenge.get(0).getDateFormatee());
		Assert.assertEquals("18 00", listeTypeChallenge.get(0).getHeureFormatee());
		Assert.assertEquals("description", listeTypeChallenge.get(0).getDescription_challenge());
		Assert.assertEquals("1", listeTypeChallenge.get(0).getId_adrChal());
		
	}
	
	@Test
	public void test_ajouterChallenge(){
		String date1 = "2014-02-01";
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		String heure1 = "18:30:00";
		Date heure2 = null;
		try {
			heure2 = heureFormat.parse(heure1);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		Challenge challenge = new Challenge("ch1", "challenge1", date2, heure2, "description1", "1");
		challengeDao.ajouterChallenge(challenge);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM challenge WHERE id_challenge = 'ch1'");
			result.next();
			Assert.assertEquals("ch1", result.getString("id_challenge"));
			Assert.assertEquals("challenge1", result.getString("nom_challenge"));
			Assert.assertEquals("2014-02-01", result.getString("date_challenge"));
			Assert.assertEquals("18:30:00", result.getString("heure_challenge"));
			Assert.assertEquals("description1", result.getString("description_challenge"));
			Assert.assertEquals("1", result.getString("id_adresse"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_supprimerChallenge(){
		challengeDao.supprimerChallenge("ch0");
		List<Challenge> listeChallenge = new ArrayList<Challenge>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM challenge");
			while(result.next()){
			 Challenge challenge = new Challenge(
					 result.getString("id_challenge"),
					 result.getString("nom_challenge"),
					 result.getDate("date_challenge"),
					 result.getTime("heure_challenge"),
					 result.getString("description_challenge"),
					 result.getString("id_adresse"));
			 listeChallenge.add(challenge);
			}
			result.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(0, listeChallenge.size());
	}

}
