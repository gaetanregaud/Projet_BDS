package bds.devweb.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seance {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	
	private String id_seance;
	private String id_etudiant;
	private String id_equipeSport;
	private Date date_seance;
	private String presence_seance;
	private Etudiant etudiant;
	
	public Seance(String id_seance, String id_etudiant, String id_equipeSport, Date date_seance, String presence_seance){
		this.id_seance = id_seance;
		this.id_etudiant = id_etudiant;
		this.id_equipeSport = id_equipeSport;
		this.date_seance = date_seance;
		this.presence_seance = presence_seance;
		etudiant = new Etudiant("", "", "", "", "", "", "", false, false, "");
	}
	
	public String getDateFormatee() {
		return dateFormat.format(date_seance);
	}

	public String getId_seance() {
		return id_seance;
	}

	public void setId_seance(String id_seance) {
		this.id_seance = id_seance;
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

	public Date getDate_seance() {
		return date_seance;
	}

	public void setDate_seance(Date date_seance) {
		this.date_seance = date_seance;
	}

	public String getPresence_seance() {
		return presence_seance;
	}

	public void setPresence_seance(String presence_seance) {
		this.presence_seance = presence_seance;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	
}
