package ru.javaops.masterjava.service.mail;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by rolep on 27/04/17.
 */
@WebService
public interface MailService {

    @WebMethod
    void SendMail(
            @WebParam(name = "to") List<Addressee> to,
            @WebParam(name = "cc") List<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body);
}
