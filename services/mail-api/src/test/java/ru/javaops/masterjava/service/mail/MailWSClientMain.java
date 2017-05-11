package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

/**
 * Created by rolep on 10/05/17.
 */
public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendEmail(
                ImmutableSet.of(new Addressee("Some Name <_rolep_@mail.ru>")),
                ImmutableSet.of(new Addressee("Naother Test <crazy.rolep@gmail.com")), "Subject", "Body"
        );
    }
}
