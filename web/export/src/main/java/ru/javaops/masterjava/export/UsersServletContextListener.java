package ru.javaops.masterjava.export;

import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by rolep on 14/04/17.
 */
@WebListener
public class UsersServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserController.init();
        UserController.cleanUsers();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       UserController.cleanUsers();
       UserController.close();
    }
}
