package bds.devweb.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class SendMail {
	
	private String mail;
	
	public SendMail(){
		
	}
	
	public static void InscriptionMail (String mail){
		
		try {
			Properties props = System.getProperties();
			               props.put("mail.smtp.host", "smtp.gmail.com");
			               props.put("mail.smtp.auth", "true");
			               props.put("mail.smtp.starttls.enable", "true");
			               props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,null);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("gaetan.regaud@gmail.com")); 
			// InternetAddress recipient = new InternetAddress(mail);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail,false));
			message.setSubject("Votre nouveau compte");
			message.setSentDate(new Date());
			message.setText("Merci de Vous êtes inscrit sur le site du BDS HEI\n"
					+"Veuillez vous connecter sur la page ci dessous pour confirmer votre inscription\n"
					+"https://localhost:8443/projet/validation");

			SMTPTransport transport = (SMTPTransport)session.getTransport("smtp"); 
			transport.connect("smtp.gmail.com","gaetan.regaud@gmail.com","Password");
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			}
		
			catch(NoSuchProviderException e) {
				System.err.println("Pas de transport disponible pour ce ￼￼￼￼protocole");
				System.err.println(e);
			} 
			catch(AddressException e) {
				System.err.println("Adresse invalide");
				System.err.println(e); }
			catch(MessagingException e) {
				System.err.println("Erreur dans le message");
				System.err.println(e);
			}
		
		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
