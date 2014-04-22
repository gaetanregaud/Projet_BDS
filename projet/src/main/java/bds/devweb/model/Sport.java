package bds.devweb.model;

public class Sport {
	
	private String id_sport;
	private String nom_sport;
	
	public Sport(String id_sport, String nom_sport){
		this.id_sport = id_sport;
		this.nom_sport = nom_sport;
	}

	public String getId_sport() {
		return id_sport;
	}

	public void setId_sport(String id_sport) {
		this.id_sport = id_sport;
	}

	public String getNom_sport() {
		return nom_sport;
	}

	public void setNom_sport(String nom_sport) {
		this.nom_sport = nom_sport;
	}

	

}
