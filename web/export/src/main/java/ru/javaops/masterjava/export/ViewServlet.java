package ru.javaops.masterjava.export;

import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.persist.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.javaops.masterjava.export.ThymeleafListener.*;
import static ru.javaops.masterjava.export.UserController.getUsers;

/**
 * Created by rolep on 13/04/17.
 */
@WebServlet("/")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext());
        List<User> users = getUsers(20);
        webContext.setVariable("users", users);
        engine.process("result", webContext, resp.getWriter());
    }
}
