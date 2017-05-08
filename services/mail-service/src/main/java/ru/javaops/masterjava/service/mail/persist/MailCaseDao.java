package ru.javaops.masterjava.service.mail.persist;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.dao.AbstractDao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by rolep on 04/05/17.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MailCaseDao implements AbstractDao {

    @Override
    @SqlUpdate("TRUNCATE mail_hist")
    public abstract void clean();

    @SqlQuery("SELECT * FROM mail_hist WHERE date>=:after ORDER BY date DESC")
    public abstract List<MailCase> getAfter(@Bind("after") Date date);

    @SqlUpdate("INSERT INTO mail_hist (list_to, list_cc, subject, body, state, date) VALUES (:listTo, :listCc, :subject, :body, :state, :date)")
    @GetGeneratedKeys
    public abstract int insert(@BindBean MailCase mails);
}