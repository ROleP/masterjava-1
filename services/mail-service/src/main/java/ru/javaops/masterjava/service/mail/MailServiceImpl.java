package ru.javaops.masterjava.service.mail;

import javax.jws.WebService;
import java.util.Set;

/**
 * gkislin
 * 15.11.2016
 */
@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService", targetNamespace = "http://mail.javaops.ru/")
public class MailServiceImpl implements MailService {
    public void sendMail(Set<Addressee> to, Set<Addressee> cc, String subject, String body) {
        MailSender.sendBulk(to, cc, subject, body);
    }
}