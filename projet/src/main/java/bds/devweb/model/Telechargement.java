package bds.devweb.model;

public class Telechargement {
	
	private String id_etudiant;
	private String nom_etudiant;
	private String prenom_etudiant;
	private float note_sport;
	private int nb_challenge;
	private float note_final;
	
	public Telechargement (String id_etudiant, String nom_etudiant, String prenom_etudiant, float note_sport, int nb_challenge){
		this.id_etudiant = id_etudiant;
		this.nom_etudiant = nom_etudiant;
		this.prenom_etudiant = prenom_etudiant;
		this.note_sport = note_sport;
		this.nb_challenge = nb_challenge;
		note_final = calcul_note(note_sport, nb_challenge);
		
	}
	
	public float calcul_note (float note_sport, int nb_challenge){
		float notefinal = 0;
		if(note_sport == 0){
			notefinal = 0;
		}
		else
		{
			if(nb_challenge < 1){
				notefinal = note_sport;
			}
			else if(nb_challenge == 1){
				notefinal = note_sport + 2;
			}
			else if(nb_challenge > 1){
				notefinal = note_sport + 4;
			}	
		}
		return notefinal;
	}

	public String getId_etudiant() {
		return id_etudiant;
	}

	public void setId_etudiant(String id_etudiant) {
		this.id_etudiant = id_etudiant;
	}

	public String getNom_etudiant() {
		return nom_etudiant;
	}

	public void setNom_etudiant(String nom_etudiant) {
		this.nom_etudiant = nom_etudiant;
	}

	public String getPrenom_etudiant() {
		return prenom_etudiant;
	}

	public void setPrenom_etudiant(String prenom_etudiant) {
		this.prenom_etudiant = prenom_etudiant;
	}

	public float getNote_sport() {
		return note_sport;
	}

	public void setNote_sport(float note_sport) {
		this.note_sport = note_sport;
	}

	public int getNb_challenge() {
		return nb_challenge;
	}

	public void setNb_challenge(int nb_challenge) {
		this.nb_challenge = nb_challenge;
	}

	public float getNote_final() {
		return note_final;
	}

	public void setNote_final(float note_final) {
		this.note_final = note_final;
	}
	
	

}
