package bds.devweb.model;

public class VP extends Etudiant{
	
	private String id_etudiant;
	private String id_equipeSport;
	private float note_vp;
	private EquipeSport equipesport;
	
	public VP(String id_etudiant, String nom, String prenom, String classe, String telephone, String mail, String photo, boolean cotisation, boolean certificat, String licence, String id_equipeSport, float note_vp){
		super(id_etudiant, nom, prenom, classe, telephone, mail, photo, cotisation, certificat, licence);
		this.id_etudiant = id_etudiant;
		this.id_equipeSport = id_equipeSport;
		this.note_vp = note_vp;
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

	public float getNote_vp() {
		return note_vp;
	}

	public void setNote_vp(float note_vp) {
		this.note_vp = note_vp;
	}

	public EquipeSport getEquipesport() {
		return equipesport;
	}

	public void setEquipesport(EquipeSport equipesport) {
		this.equipesport = equipesport;
	}

}
