package net.rolep.jpractice.threads;

/**
 * Created by rolep on 3/15/17.
 */
public class SimpleSecutiryContextHolder {

    private static final ThreadLocal<SimpleUser> threadLocalScope = new ThreadLocal<>();

    public static final SimpleUser getLoggedUser() {
        return threadLocalScope.get();
    }

    public static final void setLoggedUser(SimpleUser user) {
        threadLocalScope.set(user);
    }
}
