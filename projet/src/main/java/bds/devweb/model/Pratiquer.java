package bds.devweb.model;

public class Pratiquer {
	private String id_etudiant;
	private String id_equipeSport;
	private String note;
	private Etudiant etudiant;
	private VP vp;
	private EquipeSport equipesport;
	
	public Pratiquer(String id_etudiant, String id_equipeSport, String note){
		this.id_etudiant = id_etudiant;
		this.id_equipeSport = id_equipeSport;
		this.note = note;
		etudiant = new Etudiant("", "", "", "", "", "", "", false, false, "");
		vp = new VP("", "", "", "", "", "", "", false, false, "", "", 0);
		equipesport = new EquipeSport("", "", "", "", "", "");
	}

	public String getId_etudiant() {
		return id_etudiant;
	}

	public void setId_etudiant(String id_etudiant) {
		this.id_etudiant = id_etudiant;
	}

	public String getId_equipeSport() {
		return id_equipeSport;
	}

	public void setId_equipeSport(String id_equipeSport) {
		this.id_equipeSport = id_equipeSport;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public VP getVp() {
		return vp;
	}

	public void setVp(VP vp) {
		this.vp = vp;
	}

	public EquipeSport getEquipesport() {
		return equipesport;
	}

	public void setEquipesport(EquipeSport equipesport) {
		this.equipesport = equipesport;
	}

}
