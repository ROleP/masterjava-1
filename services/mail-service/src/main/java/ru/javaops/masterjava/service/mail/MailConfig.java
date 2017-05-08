package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import ru.javaops.masterjava.config.Configs;

import java.nio.charset.StandardCharsets;

/**
 * Created by rolep on 03/05/17.
 */
public class MailConfig {
    private static final MailConfig INSTANCE =
            new MailConfig(Configs.getConfig("mail.conf", "mail"));

    private final String host;
    private final int port;
    private final boolean useSSL;
    private final boolean useTLS;
    private final boolean debug;
    private final String username;
    private final javax.mail.Authenticator auth;
    private final String fromName;

    private MailConfig(Config conf) {
        host = conf.getString("host");
        port = conf.getInt("port");
        username = conf.getString("username");
        auth = new DefaultAuthenticator(username, conf.getString("password"));
        useSSL = conf.getBoolean("useSSL");
        useTLS = conf.getBoolean("useTLS");
        debug = conf.getBoolean("debug");
        fromName = conf.getString("fromName");
    }

    public <T extends Email> T prepareEmail(T email) throws EmailException {
        email.setFrom(username, fromName);
        email.setHostName(host);
        if (useSSL) {
            email.setSslSmtpPort(String.valueOf(port));
        } else {
            email.setSmtpPort(port);
        }
        email.setSSLOnConnect(useSSL);
        email.setStartTLSEnabled(useTLS);
        email.setDebug(debug);
        email.setAuthenticator(auth);
        email.setCharset(StandardCharsets.UTF_8.name());
        return email;
    }

    public static HtmlEmail createHtmlEmail() throws EmailException {
        return INSTANCE.prepareEmail(new HtmlEmail());
    }

    @Override
    public String toString() {
        return "MailConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", useSSL=" + useSSL +
                ", useTLS=" + useTLS +
                ", debug=" + debug +
                ", username='" + username + '\'' +
                ", fromName='" + fromName + '\'' +
                '}';
    }
}