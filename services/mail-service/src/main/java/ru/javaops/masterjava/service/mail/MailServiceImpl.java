package ru.javaops.masterjava.service.mail;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by rolep on 28/04/17.
 */
@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService")
public class MailServiceImpl implements MailService {
    @Override
    public void SendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        MailSender.sendMail(to, cc, subject, body);
    }
}
