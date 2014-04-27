package bds.devweb.model;

public class EquipeSport extends Sport{
	
	private String id_equipeSport;
	private String nom_equipeSport;
	private String description_equipeSport;
	
	public EquipeSport(String id_sport, String nom_sport, String id_equipeSport, String nom_equipeSport, String description_equipeSport){
		super(id_sport, nom_sport);
		this.id_equipeSport = id_equipeSport;
		this.nom_equipeSport = nom_equipeSport;
		this.description_equipeSport = description_equipeSport;
	}

	public String getId_equipeSport() {
		return id_equipeSport;
	}

	public void setId_equipeSport(String id_equipeSport) {
		this.id_equipeSport = id_equipeSport;
	}

	public String getNom_equipeSport() {
		return nom_equipeSport;
	}

	public void setNom_equipeSport(String nom_equipeSport) {
		this.nom_equipeSport = nom_equipeSport;
	}

	public String getDescription_equipeSport() {
		return description_equipeSport;
	}

	public void setDescription_equipeSport(String description_equipeSport) {
		this.description_equipeSport = description_equipeSport;
	}

	

}
