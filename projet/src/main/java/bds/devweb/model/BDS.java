package bds.devweb.model;

public class BDS {
	
	private String identifiant;
	private String nom_resp;
	
	public BDS(String identifiant, String nom_resp){
		
		this.identifiant = identifiant;
		this.nom_resp = nom_resp;
		
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom_resp() {
		return nom_resp;
	}

	public void setNom_resp(String nom_resp) {
		this.nom_resp = nom_resp;
	}

}
