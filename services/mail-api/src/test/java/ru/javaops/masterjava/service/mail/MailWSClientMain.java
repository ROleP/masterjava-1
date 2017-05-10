package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;

/**
 * Created by rolep on 10/05/17.
 */
public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendEmail(
                ImmutableList.of(new Addressee("Some Name <_rolep_@mail.ru>")),
                ImmutableList.of(new Addressee("Naother Test <crazy.rolep@gmail.com")), "Subject" , "Body"
        );
    }
}
