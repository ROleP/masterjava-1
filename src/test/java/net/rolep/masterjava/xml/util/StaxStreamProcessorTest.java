package net.rolep.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Test;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import static org.junit.Assert.*;

/**
 * Created by rolep on 21/03/17.
 */
public class StaxStreamProcessorTest {

    @Test
    public void readCities() throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(StaxStreamProcessorTest.class, "/myPayload.xml").openStream())) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT) {
                    if ("City".equals(reader.getLocalName())) {
                        System.out.println(reader.getElementText());
                    }
                }
            }
        }
    }

    @Test
    public void readCities2() throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(StaxStreamProcessorTest.class, "/myPayload.xml").openStream())) {
            String city;
            while ((city=processor.getElementValue("City")) != null) {
                System.out.println(city);
            }
        }
    }
}