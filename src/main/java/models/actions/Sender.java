package models.actions;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

public class Sender {
    private static final String LOG_MESSAGE_ERROR_SEND = "Ошибка отправления письма";
    private static final String HOST = "mail.smtp.host";
    private static final String SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String AUTH = "mail.smtp.auth";
    private static final String PORT = "mail.smtp.port";
    private static final String USER = "agressorjd@gmail.com";
    private static final String PASSWORD = "1182vekz73";
    private Properties properties;

    private static final Logger log = Logger.getLogger(Sender.class);


    public Sender(){
        properties = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle("email");
        properties.put(HOST, bundle.getString(HOST));
        properties.put(SOCKET_FACTORY_PORT, bundle.getString(SOCKET_FACTORY_PORT));
        properties.put(SOCKET_FACTORY_CLASS, bundle.getString(SOCKET_FACTORY_CLASS));
        properties.put(AUTH, bundle.getString(AUTH));
        properties.put(PORT, bundle.getString(PORT));
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
                return new PasswordAuthentication(USER, PASSWORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        }catch (MessagingException ex){
            log.error(LOG_MESSAGE_ERROR_SEND);
            ex.printStackTrace();
        }
    }
}
