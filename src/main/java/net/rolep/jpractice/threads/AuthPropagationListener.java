package net.rolep.jpractice.threads;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by rolep on 3/15/17.
 */

public class AuthPropagationListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        SimpleSecutiryContextHolder.setLoggedUser(null);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        SimpleUser user = (SimpleUser) request.getAttribute("logged_user");
        SimpleSecutiryContextHolder.setLoggedUser(user);
    }
}
