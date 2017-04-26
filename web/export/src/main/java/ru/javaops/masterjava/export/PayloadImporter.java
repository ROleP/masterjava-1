package ru.javaops.masterjava.export;

import lombok.Value;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by rolep on 26/04/17.
 */
public class PayloadImporter {

    private final UserImporter userImporter = new UserImporter();

    @Value
    public static class FailedEmail {
        public String emailOrRange;
        public String reason;

        @Override
        public String toString() {
            return emailOrRange + " : " + reason;
        }
    }

    public List<FailedEmail> process(InputStream is, int chunckSize) throws XMLStreamException {
        final StaxStreamProcessor processor = new StaxStreamProcessor(is);
        return userImporter.process(processor, chunckSize);
    }
}
