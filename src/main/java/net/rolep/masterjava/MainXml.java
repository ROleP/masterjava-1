package net.rolep.masterjava;

import com.google.common.io.Resources;
import net.rolep.masterjava.xml.schema.*;
import net.rolep.masterjava.xml.util.JaxbParser;
import net.rolep.masterjava.xml.util.Schemas;
import net.rolep.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class MainXml {

    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    static {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("/myPayload.xsd"));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Enter project name:");
        Scanner scanner = new Scanner(System.in);
        args = scanner.nextLine().split(" ");
        scanner.close();

        String projectName;
        if (args.length < 1 || (projectName=args[0]) == null || projectName.isEmpty()) {
            throw new IllegalArgumentException("Wrong argument provided");
        }

        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource(MainXml.class, "/myPayload.xml").openStream());

/*        for (Project project : payload.getProjects().getProject()) {
            if (project.getName().equals(projectName)) {
                break;
            }
            System.out.println("WARNING: No project with name " + projectName + " found!");
        }*/

        List<String> projectGroups = new ArrayList<>();
        for (Group group : payload.getGroups().getGroup()) {
            if (group.getProject().equals(projectName)) {
                projectGroups.add(group.getName());
            }
        }
        if (projectGroups.isEmpty()) {
            System.out.println("There is no groups with this project!");
            return;
        }

        List<User> projectUsers = new ArrayList<>();
        for (User user : payload.getUsers().getUser()) {
            for (String groupName : user.getGroups()) {
                if (projectGroups.contains(groupName)) {
                    projectUsers.add(user);
                    break;
                }
            }
        }

        if (projectUsers.isEmpty()) {
            System.out.println("No users for project " + projectName + " found!");
        } else {
            projectUsers.sort(Comparator.comparing(User::getFullName));
            for (User user : projectUsers) {
                System.out.println(user.getFullName());
            }
        }


        System.out.println("Stax Test");

        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(MainXml.class, "/myPayload.xml").openStream())) {
            XMLStreamReader reader = processor.getReader();
            QName tagName;
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT) {
                    if ("Projects".equals(reader.getLocalName())) {
                        do {
                            tagName = reader.getName();
                        }
                    } else if ("Groups".equals(reader.getLocalName())) {

                    } else if ("Users".equals(reader.getLocalName())) {

                    }
                }
            }
        }
    }
}
