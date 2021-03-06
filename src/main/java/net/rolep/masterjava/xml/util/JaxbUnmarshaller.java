package net.rolep.masterjava.xml.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by rolep on 20/03/17.
 */
public class JaxbUnmarshaller {
    private Unmarshaller unmarshaller;

    public JaxbUnmarshaller(JAXBContext context) throws JAXBException {
        unmarshaller = context.createUnmarshaller();
    }

    public synchronized void setSchema(Schema schema) {
        unmarshaller.setSchema(schema);
    }

    public synchronized Object unmarshal(InputStream inputStream) throws JAXBException {
        return unmarshaller.unmarshal(inputStream);
    }

    public synchronized Object unmarshal(Reader reader) throws JAXBException {
        return unmarshaller.unmarshal(reader);
    }

    public Object unmarshal(String string) throws JAXBException {
        return unmarshal(new StringReader(string));
    }
}
