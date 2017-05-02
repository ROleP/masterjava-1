package ru.javaops.masterjava.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.skife.jdbi.v2.tweak.ConnectionFactory;
import ru.javaops.masterjava.service.mail.dao.MailAbstractDao;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * gkislin
 * 01.11.2016
 */
@Slf4j
public class MailDBIProvider {

    private volatile static ConnectionFactory connectionFactory = null;

    private static class DBIHolder {
        static final DBI jDBI;

        static {
            final DBI dbi;
            if (connectionFactory != null) {
                log.info("Init jDBI with  connectionFactory");
                dbi = new DBI(connectionFactory);
            } else {
                try {
                    log.info("Init jDBI with  JNDI");
                    InitialContext ctx = new InitialContext();
                    dbi = new DBI((DataSource) ctx.lookup("java:/comp/env/jdbc/masterjava"));
                } catch (Exception ex) {
                    throw new IllegalStateException("PostgreSQL initialization failed", ex);
                }
            }
            jDBI = dbi;
            jDBI.setSQLLog(new SLF4JLog());
        }
    }

    public static void init(ConnectionFactory connectionFactory) {
        MailDBIProvider.connectionFactory = connectionFactory;
    }

    public static DBI getDBI() {
        return DBIHolder.jDBI;
    }

    public static <T extends MailAbstractDao> T getDao(Class<T> daoClass) {
        return DBIHolder.jDBI.onDemand(daoClass);
    }
}