package ru.javaops.masterjava.export;

import org.thymeleaf.TemplateEngine;
import ru.javaops.masterjava.common.web.ThymeleafUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by rolep on 03/04/17.
 */
@WebListener
public class ThymeleafListener implements ServletContextListener {

    public static TemplateEngine engine;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        engine = ThymeleafUtil.getTemplateEngine(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
