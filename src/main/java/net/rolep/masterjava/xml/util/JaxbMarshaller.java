package net.rolep.masterjava.xml.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.validation.Schema;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by rolep on 20/03/17.
 */
public class JaxbMarshaller {
    private Marshaller marshaller;

    public JaxbMarshaller(JAXBContext context) throws JAXBException {
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
    }

    public void setProperty(String property, Object value) throws PropertyException {
        marshaller.setProperty(property, value);
    }

    public synchronized void setSchema(Schema schema) {
        marshaller.setSchema(schema);
    }

    public String marshal(Object instance) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        marshal(instance, stringWriter);
        return stringWriter.toString();
    }

    public synchronized void marshal(Object instance, Writer writer) throws JAXBException {
        marshaller.marshal(instance, writer);
    }


}
