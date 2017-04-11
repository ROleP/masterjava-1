package ru.javaops.masterjava.persist.dao;

import ru.javaops.masterjava.persist.AbstractDao;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.DBITestProvider;

/**
 * Created by rolep on 10/04/17.
 */
public abstract class AbstractDaoTest<DAO extends AbstractDao> {
    static {
        DBITestProvider.initDBI();
    }

    protected DAO dao;

    protected AbstractDaoTest(Class<DAO> daoClass) {
        this.dao = DBIProvider.getDBI().onDemand(daoClass);
    }
}
