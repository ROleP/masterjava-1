package ru.javaops.masterjava.service.mail.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.service.mail.model.MailEntity;

import java.util.List;

/**
 * Created by rolep on 01/05/17.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MailDao implements MailAbstractDao {

    @Override
    @SqlUpdate("TRUNCATE mailsender CASCADE")
    public abstract void clean();

    @SqlUpdate("INSERT INTO mailsender (date_sent, email_to, email_cc, subject, body, mailserver, msg_id) VALUES (:dateSent, :emailTo, :emailCc, :subject, :body, :mailserver, :msgId)")
    public abstract void addEmail(@BindBean MailEntity email);

    @SqlQuery("SELECT * FROM mailsender ORDER BY date_sent DESC")
    public abstract List<MailEntity> getAll();
}
