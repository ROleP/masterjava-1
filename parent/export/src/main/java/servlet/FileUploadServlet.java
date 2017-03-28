package servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.xml.XmlUserMatcher;
import service.xml.schema.User;
import util.TeamplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by rolep on 3/27/17.
 */
@WebServlet("/upload")
@MultipartConfig(location = "/tmp")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TeamplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("1", "");
        engine.process("index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TeamplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Collection<Part> parts = req.getParts();
        String partName = "/tmp/";
        for (Part part : parts) {
            part.write(part.getName());
            partName = "/tmp/" + part.getName();
        }
        try {
            final Set<User> users = XmlUserMatcher.getUsersByStax(partName);
            context.setVariable("users", users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        engine.process("index.html", context, resp.getWriter());
    }
}
