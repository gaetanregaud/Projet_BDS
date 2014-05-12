package bds.devweb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bds.devweb.model.Sport;

public class SportDaoTestCase {
	
	SportDao sportDao = new SportDao();
	
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
		stmt.executeUpdate("INSERT INTO etudiant VALUES ('h00001', 'h00001', 'admin', 'admin', '0611223344', 'admin.admin@hei.fr', 'h00001', 'h00', '0', '1', '123456789', '1234')");
		stmt.executeUpdate("INSERT INTO sport VALUES ('s0', 'sport', 'description')");
		stmt.executeUpdate("INSERT INTO categorie VALUES ('c0', 'categorie')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es0', 'AS', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO equipe_sport VALUES ('es1', 'AS1', 'c0', 'description', 's0')");
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.close();
		connection.close();	
	}
	
	@Test
	public void test_listerSports(){
		List<Sport> listeSport = sportDao.listerSports();
		Assert.assertEquals(1, listeSport.size());
		Assert.assertEquals("s0", listeSport.get(0).getId_sport());
		Assert.assertEquals("sport", listeSport.get(0).getNom_sport());	
	}
	
	@Test
	public void test_ajouterSport(){
		Sport sport = new Sport("s1", "sport1");
		sportDao.ajouterSport(sport);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM sport WHERE id_sport = 's1'");
			result.next();
			Assert.assertEquals("s1", result.getString("id_sport"));
			Assert.assertEquals("sport1", result.getString("nom_sport"));
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_supprimerSport(){
		sportDao.supprimerSport("s0");
		List<Sport>listeSport = new ArrayList<Sport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM sport");
			if(result.next()){
				Sport sport = new Sport(
						result.getString("id_sport"),
						result.getString("nom_sport"));	
				listeSport.add(sport);
			}
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(0, listeSport.size());
	}
	
	

}
