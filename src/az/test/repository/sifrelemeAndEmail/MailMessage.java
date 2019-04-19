package az.test.repository.sifrelemeAndEmail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailMessage {
    public static void mail(String name, String surname, String link,String email,String text) {

        final String user = "xeberler8@gmail.com";//change accordingly
        final String password = "shahin97";//change accordingly

        String to = email;//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Azex mail tesdiqleme");
            message.setText("Xos gelmisiniz : " +name+" "+surname+
                    " "+text+" "+
                    "<a href='"+link+"'>"+link+"</a>","UTF-8","html");

            //send the message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
