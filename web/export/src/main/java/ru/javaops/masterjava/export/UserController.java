package ru.javaops.masterjava.export;

import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;

import java.util.List;

/**
 * Created by rolep on 13/04/17.
 */
public class UserController {

    private static UserDao userDao = null;

    static void insert(int size, List<User> users) {
        userDao.insertChunked(size, users);
    }

    static List<User> getUsers(int limit) {
        return userDao.getWithLimit(limit);
    }

    static void cleanUsers() {
        userDao.clean();
    }

    static void close() {
        DBIProvider.getDBI().close(userDao);
        userDao=null;
    }

    static void init() {
        userDao = DBIProvider.getDao(UserDao.class);
    }
}
