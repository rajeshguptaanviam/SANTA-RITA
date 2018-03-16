package com.net.parking.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.net.parking.model.User;
import com.net.parking.service.EmailService;

@Component("emailService")
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);

	public static void main(String[] args) {
		Properties emailprop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("G:\\PARKING-DEMO\\PROJECT\\RUNNING\\SANTA-RITA\\SANTA-RITA\\src\\main\\resources\\"
					+ "email.properties");
			User user = new User();
			user.setEmail("rgrajeshu@gmail.com");
			emailprop.load(input);
			new EmailServiceImpl().sentEmail(user, "", emailprop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Integer sentEmail(User user, String confirmUrl, Properties emailprop) {

		Integer result = 0;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", emailprop.getProperty("mail.smtp.host", ""));
			props.put("mail.smtp.socketFactory.port", emailprop.getProperty("mail.smtp.socketFactory.port", ""));
			props.put("mail.smtp.socketFactory.class", emailprop.getProperty("mail.smtp.socketFactory.class", ""));
			props.put("mail.smtp.auth", emailprop.getProperty("mail.smtp.auth", ""));
			props.put("mail.smtp.port", emailprop.getProperty("mail.smtp.port", ""));

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailprop.getProperty("mail.host.user", ""),
							emailprop.getProperty("mail.host.pass", ""));
				}
			});
			
			System.out.println("USER : "+(emailprop.getProperty("mail.host.user", "")));
			System.out.println("PASS : "+(emailprop.getProperty("mail.host.pass", "")));
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailprop.getProperty("mail.sent.from", "")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject(emailprop.getProperty("mail.subject", ""));
			String htmlText = (getText(user, confirmUrl));
			message.setContent(htmlText, "text/html");

			Transport.send(message);

			System.out.println("Done");
			result = 1;

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			result = 0;
		}
		return result;
	}

	private String getText(User user, String confirmUrl) {
		
		
		return ""
				+ "<html lang='en'>"
				+ "<head>"
					+ "<meta charset='UTF-8'>"
					+ "<title>Fixed table header</title>"
					+ "</head>"
					+ "<body>"
					+ "	<section>"
					+ "		<!--for demo wrap-->"
					+ "		<h1 style='color: green'>SantaRita: parking client's request form for registration</h1>"
					+ "		<div class='tbl-header'>"
					+ "			<table style='width: 100%;'>"
					+ "				<thead>"
					+ "					<tr style='color: white;background-color: blue;'>"
					+ "						<th style='width: 20%;'>UserName</th>"
					+ "						<th style='width: 40%;'>Email</th>"
					+ "						<th style='width: 20%;'>Role</th>"
					+ "						<th style='width: 20%;'>Action</th>"
					+ "					</tr>"
					+ "				</thead>"
					+ "			</table>"
					+ "		</div>"
					+ "		<div class='tbl-content'>"
					+ "			<table style='width: 100%;'>"
					+ "				<tbody>"
					+ "					<tr style='color: white;background-color: gray; text-align: center;'>"
					+ "						<td style='width: 20%;'>rajesh</td>"
					+ "						<td style='width: 40%;'>rgupta@anviam.com</td>"
					+ "						<td style='width: 20%;'>ADMNIN</td>"
					+ "						<td style='width: 20%;color: white;cursor: pointer;'><a style='color: white;' href='"+confirmUrl+"?ID="+user.getEmail()+"&status=1'>Active</a></td>"
					+ "					</tr>"
					+ "				</tbody>"
					+ "			</table>"
					+ "		</div>"
					+ "	</section>"
					+ "</body>"
					+ "</html>"
					+ ""
					+ "";
	}

}
