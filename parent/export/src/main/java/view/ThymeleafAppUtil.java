package view;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by rolep on 3/27/17.
 */
public class ThymeleafAppUtil {

    private static TemplateEngine templateEngine;

    static {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(null);

    }
}
