package service.xml;

import service.xml.schema.FlagType;
import service.xml.schema.User;
import service.xml.util.StaxStreamProcessor;

import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by rolep on 3/28/17.
 */
public class XmlUserMatcher {

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getValue).thenComparing(User::getEmail);

    public static Set<User> getUsersByStax(String payloadName) throws Exception {
        try (InputStream is = new FileInputStream(payloadName)) {
            StaxStreamProcessor processor = new StaxStreamProcessor(is);
            Set<User> users = new TreeSet<>(USER_COMPARATOR);

            // Users loop
            while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
                User user = new User();
                user.setEmail(processor.getAttribute("email"));
                user.setCity(processor.getAttribute("city"));
                user.setFlag(FlagType.fromValue(processor.getAttribute("flag")));
                user.setValue(processor.getReader().getElementText());
                users.add(user);
            }
            return users;
        }
    }
}
