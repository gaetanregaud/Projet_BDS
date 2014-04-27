package bds.devweb.model;

public class Participer {
	
	private String id_etudiant;
	private String id_challenge;
	private String presence;
	private Etudiant etudiant;
	private Challenge challenge;
	
	public Participer(String id_etudiant, String id_challenge, String presence){
		this.id_etudiant = id_etudiant;
		this.id_challenge = id_challenge;
		this.presence = presence;
		etudiant = new Etudiant("", "", "", "", "", "", "", false, false, "");
		challenge = new Challenge("", "", null, null, "", "");
	}

	public String getId_etudiant() {
		return id_etudiant;
	}

	public void setId_etudiant(String id_etudiant) {
		this.id_etudiant = id_etudiant;
	}

	public String getId_challenge() {
		return id_challenge;
	}

	public void setId_challenge(String id_challenge) {
		this.id_challenge = id_challenge;
	}

	public String getPresence() {
		return presence;
	}

	public void setPresence(String presence) {
		this.presence = presence;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

}
