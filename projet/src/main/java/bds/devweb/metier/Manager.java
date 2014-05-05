package bds.devweb.metier;

import java.util.List;

import bds.devweb.dao.AdresseDao;
import bds.devweb.dao.BDSDao;
import bds.devweb.dao.CalendrierDao;
import bds.devweb.dao.ChallengeDao;
import bds.devweb.dao.EquipeSportDao;
import bds.devweb.dao.EtudiantDao;
import bds.devweb.dao.NoteVPDao;
import bds.devweb.dao.ParticiperDao;
import bds.devweb.dao.PratiquerDao;
import bds.devweb.dao.SeanceDao;
import bds.devweb.dao.SportDao;
import bds.devweb.dao.VPDao;
import bds.devweb.model.Adresse;
import bds.devweb.model.BDS;
import bds.devweb.model.Challenge;
import bds.devweb.model.DateCalendrier;
import bds.devweb.model.EquipeSport;
import bds.devweb.model.Etudiant;
import bds.devweb.model.NoteVP;
import bds.devweb.model.Participer;
import bds.devweb.model.Pratiquer;
import bds.devweb.model.Seance;
import bds.devweb.model.Sport;
import bds.devweb.model.VP;

public class Manager {
	
	private static Manager instance;
	
	private EtudiantDao etudiantDao;
	private SportDao sportDao;
	private VPDao vpDao;
	private SeanceDao seanceDao;
	private PratiquerDao pratiquerDao;
	private EquipeSportDao equipesportDao;
	private BDSDao bdsDao;
	private ParticiperDao participerDao;
	private ChallengeDao challengeDao;
	private CalendrierDao calendrierDao;
	private AdresseDao adresseDao;
	private NoteVPDao notevpDao;
	
	public static Manager getInstance(){
		if(instance == null){
			instance = new Manager();
		}
		return instance;
	}
	
	private Manager(){
		etudiantDao = new EtudiantDao();
		sportDao = new SportDao();
		vpDao = new VPDao();
		seanceDao = new SeanceDao();
		pratiquerDao = new PratiquerDao();
		equipesportDao = new EquipeSportDao();
		bdsDao = new BDSDao();
		participerDao = new ParticiperDao();
		challengeDao = new ChallengeDao();
		calendrierDao = new CalendrierDao();
		adresseDao = new AdresseDao();
		notevpDao = new NoteVPDao();
	}
	
	//Requète pour un EtudiantDAO
	public boolean seConnecterEtudiant(String identifiant, String password){
		return etudiantDao.seConnecter(identifiant, password);
	}
	
	public Etudiant getEtudiant(String id_etudiant){
		return etudiantDao.getEtudiant(id_etudiant);
	}
	
	public void modifEtudiant(Etudiant etudiant){
		etudiantDao.modifEtudiant(etudiant);
	}
	
	public List<Etudiant> listerEtudiant(){
		return etudiantDao.listerEtudiant();
	}
	
	public void inscriptionEtudiant(Etudiant etudiant, String password){
		etudiantDao.inscriptionEtudiant(etudiant, password);
	}
	
	// Requète pour VPDao
	
	public boolean seConnecterVP(String identifiant, String password){
		return vpDao.seConnecter(identifiant, password);
	}
	
	public VP getVP(String id_vp){
		return vpDao.getVP(id_vp);
	}
	
	public List<VP> listerVP(){
		return vpDao.listerVP();
	}
	
	public void ajouterVPForNewEquipeSport (String id_etudiant, String id_equipeSport){
		vpDao.ajouterVPForNewEquipeSport(id_etudiant, id_equipeSport);
	}
	
	public void modifierVPForExistEquipeSport (String id_etudiant, String id_equipeSport){
		vpDao.modifierVPForExistEquipeSport(id_etudiant, id_equipeSport);
	}
	
	public void modifierNoteVP (String id_vp, String id_equipeSport, float moyenne){
		vpDao.modifierNoteVP(id_vp, id_equipeSport, moyenne);
	}
	
	public VP getVPAndEquipeSport (String id_equipeSport){
		return vpDao.getVPAndEquipeSport(id_equipeSport);
	}
	
	// Requète pour BDSDao
	
	public boolean seConnecterBDS(String identifiant, String password){
		return bdsDao.seConnecterBDS(identifiant, password);
	}
	
	public BDS getBDS(String id_bds){
		return bdsDao.getBDS(id_bds);
	}
	
	// Requète pour SportDao
	
	public List<Sport> listerSports() {
		return sportDao.listerSports();
	}
	
	public void ajouterSport (Sport sport){
		sportDao.ajouterSport(sport);
	}
	
	public void supprimerSport(String id_sport){
		sportDao.supprimerSport(id_sport);
	}
	
	// Requète pour EquipeSportDao
	
	public List<EquipeSport> listerEquipeSport(){
		return equipesportDao.listerEquipeSport();
	}
	
	public List<EquipeSport> listerEquipeSportbySport(String id_sport){
		return equipesportDao.listerEquipeSportsbySport(id_sport);
	}
	
	public void supprimerEquipeSport(String id_equipeSport){
		equipesportDao.supprimerEquipeSport(id_equipeSport);
	}
	
	public void ajouterEquipeSport (EquipeSport equipesport){
		equipesportDao.ajouterEquipeSport(equipesport);
	}
	
	// Requète pour PratiquerDao
	
	public List<Pratiquer> listerPratiqueforEtudiant(String id_etudiant) {
		return pratiquerDao.listerPratiqueforEtudiant(id_etudiant);
	}
	
	public  List<Pratiquer> listerPratiquantbyEquipeSport (String id_equipeSport){
		return pratiquerDao.listerPratiquantbyEquipeSport(id_equipeSport);
	}
	
	public void ajouterPratique (Pratiquer pratique){
		pratiquerDao.ajouterPratique(pratique);
	}
	
	// Requète pour SeanceDao
	
	public List<Seance> listerSeancebyIdforEtudiant(String id_etudiant){
		return seanceDao.listerSeancebyIdforEtudiant(id_etudiant);
	}
	
	public List<Seance> listerSeancebyNumforEquipeSport(String id_equipeSport){
		return seanceDao.listerSeancebyNumforEquipeSport(id_equipeSport);
	}
	
	public List<Seance> listerSeancebyIdforEquipeSport(String id_equipeSport){
		return seanceDao.listerSeancebyIdforEquipeSport(id_equipeSport);
	}
	
	public List<Seance> listerNumeroSeancebyEquipeSport(String id_equipeSport){
		return seanceDao.listerNumeroSeancebyEquipeSport(id_equipeSport);
	}
	
	public void ajouterSeance (Seance seance){
		seanceDao.ajouterSeance(seance);
	}
	
	// Requète pour NoteVPDao
	
	public void ajouterNoteVP(NoteVP notevp){
		notevpDao.ajouterNoteVP(notevp);
	}
	
	public List<NoteVP> listerNoteVP(String id_vp, String id_equipeSport){
		return notevpDao.listerNoteVP(id_vp, id_equipeSport);
	}
	
	//  Requète pour ChallengeDao
	
	public Challenge getChallenge (String id_challenge){
		return challengeDao.getChallenge(id_challenge);
	}
	
	public Challenge getLastChallenge (String nom_challenge){
		return challengeDao.getLastChallenge(nom_challenge);
	}
	
	public void modifierChallenge(Challenge challenge){
		challengeDao.modifierChallenge(challenge);
	}
	
	public List<Challenge> listerChallenge(){
		return challengeDao.listerChallenge();
	}
	
	public List<Challenge> listerTypeChallenge(){
		return challengeDao.listerTypeChallenge();
	}
	
	public void ajouterChallenge(Challenge challenge){
		challengeDao.ajouterChallenge(challenge);
	}
	
	public void supprimerChallenge (String id_challenge){
		challengeDao.supprimerChallenge(id_challenge);
	}
	
	// Requète pour AdresseDao
	
	public List<Adresse> listerAdresse(){
		return adresseDao.listerAdresse();
	}
	
	public Adresse getAdresseByNom(String nom_adresse){
		return adresseDao.getAdresseByNom(nom_adresse);
	}
	
	public void ajouterAdresse(Adresse adresse){
		adresseDao.ajouterAdresse(adresse);
	}
	
	// Requète pour ParticiperDao
	
	public List<Participer> listerParticipationforEtudiant(String id_etudiant){
		return participerDao.listerParticipationforEtudiant(id_etudiant);
	}
	
	public List<Participer> listerParticipationByChallenge (String id_challenge){
		return participerDao.listerParticipationByChallenge(id_challenge);
	}
	
	public List<Participer> listerParticipation(){
		return participerDao.listerParticipation();
	}
	
	public void ajouterParticipationToChallenge (Participer participer){
		participerDao.ajouterParticipationToChallenge(participer);
	}
	
	public void modifierPresence (Participer participer){
		participerDao.modifierPresence(participer);
	}
	
	//Requète pour DateCalendrierDao
	
	public List<DateCalendrier> calendrier(int annee1, int annee2){
		return calendrierDao.calendrier(annee1, annee2);
	}
	
	
}
