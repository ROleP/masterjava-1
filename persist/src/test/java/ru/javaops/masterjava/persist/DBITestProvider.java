package ru.javaops.masterjava.persist;

import java.sql.DriverManager;

/**
 * Created by rolep on 10/04/17.
 */
public class DBITestProvider {
    public static void initDBI() { initDBI("jdbc:postgresql://localhost:5432/masterjava", "user", "password"); }

    public static void initDBI(final String dbUrl, final String dbUser, final String dbPassword) {
        DBIProvider.init(() -> {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("PostgreSQL driver not found", e);
            }
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        });
    }
}
