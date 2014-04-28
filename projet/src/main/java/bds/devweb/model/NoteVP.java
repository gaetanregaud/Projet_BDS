package bds.devweb.model;

public class NoteVP {
	
	private String id_etudiant;
	private String id_vp;
	private String id_equipeSport;
	private float note1;
	private float note2;
	private float note3;
	private float note4;
	private float note5;
	private float note6;
	private float note7;
	private float note8;
	private float note9;
	private float note10;
	private float resultat;
	
	public NoteVP(String id_etudiant, String id_vp, String id_equipeSport, float note1, float note2, float note3, float note4, float note5, float note6, float note7, float note8, float note9, float note10, float resultat){
		this.id_etudiant = id_etudiant;
		this.id_vp = id_vp;
		this.id_equipeSport = id_equipeSport;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.note6 = note6;
		this.note7 = note7;
		this.note8 = note8;
		this.note9 = note9;
		this.note10 = note10;
		this.resultat = resultat;
	}

	public String getId_etudiant() {
		return id_etudiant;
	}

	public void setId_etudiant(String id_etudiant) {
		this.id_etudiant = id_etudiant;
	}

	public String getId_vp() {
		return id_vp;
	}

	public void setId_vp(String id_vp) {
		this.id_vp = id_vp;
	}

	public String getId_equipeSport() {
		return id_equipeSport;
	}

	public void setId_equipeSport(String id_equipeSport) {
		this.id_equipeSport = id_equipeSport;
	}

	public float getNote1() {
		return note1;
	}

	public void setNote1(float note1) {
		this.note1 = note1;
	}

	public float getNote2() {
		return note2;
	}

	public void setNote2(float note2) {
		this.note2 = note2;
	}

	public float getNote3() {
		return note3;
	}

	public void setNote3(float note3) {
		this.note3 = note3;
	}

	public float getNote4() {
		return note4;
	}

	public void setNote4(float note4) {
		this.note4 = note4;
	}

	public float getNote5() {
		return note5;
	}

	public void setNote5(float note5) {
		this.note5 = note5;
	}

	public float getNote6() {
		return note6;
	}

	public void setNote6(float note6) {
		this.note6 = note6;
	}

	public float getNote7() {
		return note7;
	}

	public void setNote7(float note7) {
		this.note7 = note7;
	}

	public float getNote8() {
		return note8;
	}

	public void setNote8(float note8) {
		this.note8 = note8;
	}

	public float getNote9() {
		return note9;
	}

	public void setNote9(float note9) {
		this.note9 = note9;
	}

	public float getNote10() {
		return note10;
	}

	public void setNote10(float note10) {
		this.note10 = note10;
	}

	public float getResultat() {
		return resultat;
	}

	public void setResultat(float resultat) {
		this.resultat = resultat;
	}
	
	

}
