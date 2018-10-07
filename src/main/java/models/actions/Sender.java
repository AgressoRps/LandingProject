package models.actions;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

public class Sender {
    private static final String userName = "agressorjd@gmail.com";
    private String password = "1182vekz73";
    private Properties properties;

    public Sender(){
        properties = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle("email");
        properties.put("mail.smtp.host", bundle.getString("host"));
        properties.put("mail.smtp.socketFactory.port", bundle.getString("socketFactory.port"));
        properties.put("mail.smtp.socketFactory.class", bundle.getString("socketFactory.class"));
        properties.put("mail.smtp.auth", bundle.getString("auth"));
        properties.put("mail.smtp.port", bundle.getString("port"));
    }

    /**
     * Метод формирует оообщение, а затем отправляет его по электронной почте
     * @param subject тема сообщения
     * @param text текс сообщения
     * @param toEmail получатель сообщения
     */
    public void send(String subject, String text, String toEmail){
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        }catch (MessagingException ex){
            ex.printStackTrace();
        }
    }
}
