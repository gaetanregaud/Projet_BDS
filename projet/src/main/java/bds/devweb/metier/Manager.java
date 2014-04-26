package bds.devweb.metier;

import java.util.List;

import bds.devweb.dao.AdresseDao;
import bds.devweb.dao.BDSDao;
import bds.devweb.dao.CalendrierDao;
import bds.devweb.dao.ChallengeDao;
import bds.devweb.dao.EquipeSportDao;
import bds.devweb.dao.EtudiantDao;
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
		
	}
	
	//Requ��te pour un ��tudiant
	public boolean seConnecterEtudiant(String identifiant, String password){
		return etudiantDao.seConnecter(identifiant, password);
	}
	
	public boolean EtudiantExiste(String identifiant){
		return etudiantDao.EtudiantExist(identifiant);
	}
	
	public Etudiant getEtudiant(String identifiant){
		return etudiantDao.getEtudiant(identifiant);
	}
	
	public List<Pratiquer> listerPratiquerforEtudiant(String identifiant) {
		List<Pratiquer> liste = pratiquerDao.listerPratiquerforEtudiant(identifiant);
		return liste;
	}
	
	public List<Seance> listerSeancebyIdforEtudiant(String identifiant){
		return seanceDao.listerSeancebyIdforEtudiant(identifiant);
	}
	
	public List<Participer> listerParticipationforEtudiant(String identifiant){
		return participerDao.listerParticipationforEtudiant(identifiant);
	}
	
	public List<Challenge> listerChallenge(){
		return challengeDao.listerChallenge();
	}
	
	public void ajouterParticipationToChallenge (Participer participer){
		participerDao.AjouterParticipationToChallenge(participer);
	}
	
	//Requ��te pour un VP
	public boolean seConnecterVP(String identifiant, String password){
		return vpDao.seConnecter(identifiant, password);
	}
	
	public VP getVP(String identifiant){
		return vpDao.getVP(identifiant);
	}
	public  List<Pratiquer> listerPratiquantbyVP(String identifiant){
		return pratiquerDao.listerPratiquantbyVP(identifiant);
	}
	
	public  List<Pratiquer> listerPratiquantNotebyVP(String identifiant){
		return pratiquerDao.listerPratiquantNotebyVP(identifiant);
	}
	
	public List<EquipeSport> listerEquipeSportbySport(String identifiant){
		return equipesportDao.listerEquipeSportsbySport(identifiant);
	}
	
	public List<Seance> listerSeancebyNumforEquipeSport(String identifiant){
		return seanceDao.listerSeancebyNumforEquipeSport(identifiant);
	}
	
	public List<Seance> listerSeancebyIdforEquipeSport(String identifiant){
		return seanceDao.listerSeancebyIdforEquipeSport(identifiant);
	}
	
	public List<Seance> listerNumeroSeancebyEquipeSport(String identifiant){
		return seanceDao.listerNumeroSeancebyEquipeSport(identifiant);
	}
	
	public void ajouterSeance (Seance seance){
		seanceDao.AjouterSeance(seance);
	}
	
	public void deleteChallenge (String id_challenge){
		challengeDao.deleteChallenge(id_challenge);
	}
	
	
	//Requ��te pour la barre de menus
	public List<Sport> listerLiSports() {
		List<Sport> liste = sportDao.listerLiSports();
		return liste;
	}
	//Requ��te pour une personne du BDS
	public boolean seConnecterBDS(String identifiant, String password){
		return bdsDao.seConnecterBDS(identifiant, password);
	}
	public BDS getBDS(String identifiant){
		return bdsDao.getBDS(identifiant);
	}
	public List<Adresse> listerAdresse(){
		return adresseDao.ListerAdresse();
	}
	
	public List<Participer> listerParticipation(){
		return participerDao.listerParticipation();
	}
	
	public List<Participer> listerParticipationByChallenge (String id_challenge){
		return participerDao.listerParticipationByChallenge(id_challenge);
	}
	
	public void modifierPresence (String id_etudiant, String id_challenge, String presence){
		participerDao.ModifierPresence(id_etudiant, id_challenge, presence);
	}
	
	public List<Challenge> listerTypeChallenge(){
		return challengeDao.listerTypeChallenge();
	}
	
	public void ajouterChallenge(Challenge challenge){
		challengeDao.AjouterChallenge(challenge);
	}
	
	public void ajouterAdresse(Adresse adresse){
		adresseDao.AjouterAdresse(adresse);
	}

	public List<Etudiant> listerID_Etudiant(String id_etudiant){
		return etudiantDao.listerID_Etudiant(id_etudiant);
	}
	
	public List<Etudiant> listerEtudiantNotOk(){
		return etudiantDao.listerEtudiantNotOK();
	}
	
	public List<EquipeSport> listerEquipeSport(){
		return equipesportDao.listerEquipeSport();
	}
	
	public void supprimerEquipeSport(String id_equipeSport){
		equipesportDao.deleteEquipeSport(id_equipeSport);
	}
	
	public void supprimerSport(String id_sport){
		sportDao.deleteSport(id_sport);
	}
	
	public void modifierVPforEquipeSport(String id_equipeSport, String id_etudiant){
		vpDao.ModifierVPForEquipeSport(id_equipeSport, id_etudiant);
	}
	
	public void ajouterEquipeSport (String id_equipeSport, String nom_equipeSport, String id_categorie, String id_sport){
		equipesportDao.ajouterEquipeSport(id_equipeSport, nom_equipeSport, id_categorie, id_sport);
	}
	
	public void ajouterVP (String id_etudiant, String id_equipeSport){
		vpDao.AjouterVP(id_etudiant, id_equipeSport);
	}
	
	public void ajouterSport (String id_sport, String nom_sport){
		sportDao.ajouterSport(id_sport, nom_sport);
	}
	
	public Challenge getChallenge (String id_challenge){
		return challengeDao.getChallenge(id_challenge);
	}
	
	//Calendrier
	public List<DateCalendrier> calendrier(int annee1, int annee2){
		return calendrierDao.calendrier(annee1, annee2);
	}
	
	public List<VP> listerVP(){
		return vpDao.listerVP();
	}
}
