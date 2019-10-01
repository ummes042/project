package com.aud.demo.mail;

import javax.mail.Authenticator;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.aud.demo.model.User;

public class Mail {
	
	public boolean sendMail(User user) {
		
		final String fromEmail = "kbnceplc@gmail.com"; //requires valid gmail id
		final String password = "Khaja8N@w@z@047"; // correct password for gmail id
		final String toEmail = user.getEmail(); // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		String msg = "Dear "+user.getFname()+"\n Please find below OTP details and verify your account\nOTP:"+user.getOtp();
		sendEmail(session, toEmail,"International Jounral of Science: OTP", msg);
		
		
		
		return true;
	}
	
	 private void sendEmail(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("IJS@gmail.com", "NoReply-IJS"));

	      msg.setReplyTo(InternetAddress.parse("IJS@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());
              
	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      
            
              System.out.println("Message is ready");
              Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}


}
