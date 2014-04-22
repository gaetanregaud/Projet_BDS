package bds.devweb.model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Challenge{
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	private static DateFormat heureFormat = new SimpleDateFormat("HH mm");
	private String id_challenge;
	private String nom_challenge;
	private Date date_challenge;
	private Date heure_challenge;
	private String description_challenge;
	private String id_adrChal;
	private Adresse adresse;
	
	public Challenge(String id_challenge, String nom_challenge, Date date_challenge, Date heure_challenge, String description_challenge, String id_adrChal){
		this.id_challenge = id_challenge;
		this.nom_challenge = nom_challenge;
		this.date_challenge = date_challenge;
		this.heure_challenge = heure_challenge;
		this.description_challenge = description_challenge;
		this.id_adrChal = id_adrChal;
		adresse = new Adresse("", "", "", "", "", "", "");

	}
	
	public String getDateFormatee() {
		return dateFormat.format(date_challenge);
	}
	
	public String getHeureFormatee() {
		return heureFormat.format(heure_challenge);
	}

	public String getId_challenge() {
		return id_challenge;
	}

	public void setId_challenge(String id_challenge) {
		this.id_challenge = id_challenge;
	}

	public String getNom_challenge() {
		return nom_challenge;
	}

	public void setNom_challenge(String nom_challenge) {
		this.nom_challenge = nom_challenge;
	}

	public Date getDate_challenge() {
		return date_challenge;
	}

	public void setDate_challenge(Date date_challenge) {
		this.date_challenge = date_challenge;
	}

	public String getDescription_challenge() {
		return description_challenge;
	}

	public void setDescription_challenge(String description_challenge) {
		this.description_challenge = description_challenge;
	}

	public String getId_adrChal() {
		return id_adrChal;
	}

	public void setId_adrChal(String id_adrChal) {
		this.id_adrChal = id_adrChal;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Date getHeure_challenge() {
		return heure_challenge;
	}

	public void setHeure_challenge(Date heure_challenge) {
		this.heure_challenge = heure_challenge;
	}
	
	

}

	
