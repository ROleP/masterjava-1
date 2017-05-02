package ru.javaops.masterjava.service.mail.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by rolep on 02/05/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailEntity extends MailBaseEntity {

    @Column("date_sent")
    private LocalDateTime dateSent;
    @Column("email_to")
    private String[] emailTo;
    @Column("email_cc")
    private String[] emailCc;
    private String subject;
    private String body;
    private String mailserver;
    private String msg_id;
}
