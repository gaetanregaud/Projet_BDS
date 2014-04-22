package bds.devweb.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCalendrier {
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	
	private int annee;
	private int mois;
	private int jour;
	private Date date = new Date();
	private int jourDeLaSemaine;
	
	public DateCalendrier(int annee, int mois, int jour, int jourDeLaSemaine){
		date.setYear(annee);
		date.setMonth(mois);
		date.setDate(jour);
		this.jourDeLaSemaine = jourDeLaSemaine;
			
	}

	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDateFormatee() {
		return dateFormat.format(date);
	}
	
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getJourDeLaSemaine() {
		return jourDeLaSemaine;
	}

	public void setJourDeLaSemaine(int jourDeLaSemaine) {
		this.jourDeLaSemaine = jourDeLaSemaine;
	}

}
