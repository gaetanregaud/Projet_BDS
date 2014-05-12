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

import bds.devweb.model.EquipeSport;


public class EquipeSportDaoTestCase {
	
	EquipeSportDao equipesportDao = new EquipeSportDao();
	
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
		stmt.executeUpdate("INSERT INTO vp VALUES ('h00000', 'es0', 0.0)");
		stmt.executeUpdate("INSERT INTO bds VALUES ('h00000', 'nomResp')");
		stmt.close();
		connection.close();	
	}

	@Test
	public void test_listerEquipeSportsbySport(){
		List<EquipeSport> listeEquipeSportbySport = equipesportDao.listerEquipeSportsbySport("s0");
		Assert.assertEquals(1, listeEquipeSportbySport.size());
		Assert.assertEquals("es0", listeEquipeSportbySport.get(0).getId_equipeSport());
		Assert.assertEquals("AS", listeEquipeSportbySport.get(0).getNom_equipeSport());
		Assert.assertEquals("c0", listeEquipeSportbySport.get(0).getId_categoire());
		Assert.assertEquals("description", listeEquipeSportbySport.get(0).getDescription_equipeSport());
		Assert.assertEquals("s0", listeEquipeSportbySport.get(0).getId_sport());
		Assert.assertEquals("sport", listeEquipeSportbySport.get(0).getNom_sport());
		
	}
	
	@Test
	public void test_listerEquipeSports(){
		List<EquipeSport> listeEquipeSport = equipesportDao.listerEquipeSport();
		Assert.assertEquals(1, listeEquipeSport.size());
		Assert.assertEquals("es0", listeEquipeSport.get(0).getId_equipeSport());
		Assert.assertEquals("AS", listeEquipeSport.get(0).getNom_equipeSport());
		Assert.assertEquals("c0", listeEquipeSport.get(0).getId_categoire());
		Assert.assertEquals("description", listeEquipeSport.get(0).getDescription_equipeSport());
		Assert.assertEquals("s0", listeEquipeSport.get(0).getId_sport());
		Assert.assertEquals("sport", listeEquipeSport.get(0).getNom_sport());
		
	}
	
	@Test
	public void test_supprimerEquipeSport(){
		equipesportDao.supprimerEquipeSport("es0");
		List<EquipeSport>listeEquipeSport = new ArrayList<EquipeSport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT equipe_sport.*, sport.* FROM equipe_sport INNER JOIN sport ON equipe_sport.id_sport = sport.id_sport");
			if(result.next()){
				EquipeSport equipesport = new EquipeSport(
						result.getString("sport.id_sport"),
						result.getString("sport.nom_sport"),
						result.getString("equipe_sport.id_equipeSport"), 
						result.getString("equipe_sport.nom_equipeSport"), 
						result.getString("equipe_sport.id_categorie"),
						result.getString("equipe_sport.description_equipeSport"));	
				listeEquipeSport.add(equipesport);
			}
			Assert.assertEquals(0, listeEquipeSport.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_ajouterEquipeSport(){
		EquipeSport equipesport = new EquipeSport("s0", "", "es1", "AS1", "c0", "description1");
		equipesportDao.ajouterEquipeSport(equipesport);
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT equipe_sport.*, sport.* FROM equipe_sport INNER JOIN sport ON equipe_sport.id_sport = sport.id_sport WHERE equipe_sport.id_equipeSport = 'es1'");
			result.next();
			Assert.assertEquals("s0", result.getString("sport.id_sport"));
			Assert.assertEquals("sport", result.getString("sport.nom_sport"));
			Assert.assertEquals("es1", result.getString("equipe_sport.id_equipeSport"));
			Assert.assertEquals("AS1", result.getString("equipe_sport.nom_equipeSport"));
			Assert.assertEquals("c0", result.getString("equipe_sport.id_categorie"));
			Assert.assertEquals("description1", result.getString("equipe_sport.description_equipeSport"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
