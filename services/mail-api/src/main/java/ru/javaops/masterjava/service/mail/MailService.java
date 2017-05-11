package ru.javaops.masterjava.service.mail;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Set;

/**
 * gkislin
 * 15.11.2016
 */
@WebService(targetNamespace = "http://mail.javaops.ru/")
public interface MailService {

    @WebMethod
    void sendMail(
            @WebParam(name = "to") Set<Addressee> to,
            @WebParam(name = "cc") Set<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body);
}