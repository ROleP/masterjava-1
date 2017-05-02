package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import ru.javaops.masterjava.config.Configs;

import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * gkislin
 * 15.11.2016
 */
@Slf4j
public class MailSender {
    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        log.info("Send mail to \'" + to + "\' cc \'" + cc + "\' subject \'" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        Config mailConfig = Configs.getConfig("mail.conf", "mail");
        Email email = new SimpleEmail();
        email.setHostName(mailConfig.getString("host"));
        email.setSmtpPort(mailConfig.getInt("port"));
        email.setAuthentication(mailConfig.getString("username"), mailConfig.getString("password"));
        email.setDebug(mailConfig.getBoolean("debug"));
        email.setStartTLSEnabled(mailConfig.getBoolean("useTLS"));
        email.setSSLOnConnect(mailConfig.getBoolean("useSSL"));

        try {
            email.setFrom(mailConfig.getString("fromName"));
            email.setTo(to.stream().map(MailSender::addresseeToInternetAddress).filter(Objects::nonNull).collect(Collectors.toList()));
//            email.setCc(cc.stream().map(MailSender::addresseeToInternetAddress).filter(Objects::nonNull).collect(Collectors.toList()));
            email.setSubject(subject);
            email.setMsg(body);

            String result = email.send();
            log.info("email succeessfully sent, messageid: " + result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static InternetAddress addresseeToInternetAddress(Addressee addressee) {
        try {
            log.debug("Converting addressee: " + addressee.getName() + " ; " + addressee.getEmail());
            return new InternetAddress(addressee.getEmail(), addressee.getName(), "utf8");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
