package ru.javaops.masterjava.persist;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.SLF4JLog;
import org.skife.jdbi.v2.tweak.ConnectionFactory;
import org.slf4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by rolep on 07/04/17.
 */
public class DBIProvider {
    private static final Logger LOG = getLogger(DBIProvider.class);
    private volatile static ConnectionFactory connectionFactory = null;

    private static class DBIHolder {
        final static DBI jDBI;

        static {
            final DBI dbi;
            if (connectionFactory != null) {
                LOG.info("Init jDBI with connectionFactory");
                dbi = new DBI(connectionFactory);
            } else {
                try {
                    LOG.info("Init jDBI with JNDI");
                    InitialContext ctx = new InitialContext();
                    dbi = new DBI((DataSource) ctx.lookup("java:/comp/env/jdbc/masterjava"));
                } catch (Exception e) {
                    throw new IllegalArgumentException("PostgreSQL initialization failed", e);
                }
            }
            jDBI = dbi;
            jDBI.setSQLLog(new SLF4JLog());
        }
    }

    public static void init(ConnectionFactory connectionFactory) {
        DBIProvider.connectionFactory = connectionFactory;
    }

    public static DBI getDBI() {
        return DBIHolder.jDBI;
    }

    public static <T extends AbstractDao> T getDao(Class<T> daoClass) {
        return DBIHolder.jDBI.onDemand(daoClass);
    }
}