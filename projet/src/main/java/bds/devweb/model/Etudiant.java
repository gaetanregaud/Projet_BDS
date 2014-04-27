package bds.devweb.model;

public class Etudiant {
	
	private String identifiant;
	private String nom;
	private String prenom;
	private String classe;
	private String telephone;
	private String mail;
	private String photo;
	private boolean cotisation;
	private boolean certificat;
	private String licence;
	
	public Etudiant (String identifiant, String nom, String prenom, String classe, String telephone, String mail, String photo, boolean cotisation, boolean certificat, String licence){
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.classe = classe;
		this.telephone = telephone;
		this.mail = mail;
		this.photo = photo;
		this.cotisation = cotisation;
		this.certificat = certificat;
		this.licence = licence;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isCotisation() {
		return cotisation;
	}

	public void setCotisation(boolean cotisation) {
		this.cotisation = cotisation;
	}

	public boolean isCertificat() {
		return certificat;
	}

	public void setCertificat(boolean certificat) {
		this.certificat = certificat;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}
	
	
}
