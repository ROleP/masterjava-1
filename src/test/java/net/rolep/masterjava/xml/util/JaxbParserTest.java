package net.rolep.masterjava.xml.util;

import com.google.common.io.Resources;
import net.rolep.masterjava.xml.schema.CityType;
import net.rolep.masterjava.xml.schema.ObjectFactory;
import net.rolep.masterjava.xml.schema.Payload;
import org.junit.Test;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


/**
 * Created by rolep on 21/03/17.
 */
public class JaxbParserTest {
    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    static {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("/myPayload.xsd"));
    }

    @Test
    public void testPayload() throws Exception {
        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource(JaxbParserTest.class, "/myPayload.xml").openStream());
        String strPayload = JAXB_PARSER.marshal(payload);
        JAXB_PARSER.validate(strPayload);
        System.out.println(strPayload);
    }

    @Test
    public void testCity() throws Exception {
        JAXBElement<CityType> cityElement = JAXB_PARSER.unmarshal(Resources.getResource(JaxbParserTest.class, "/myCity.xml").openStream());
        CityType city = cityElement.getValue();
        JAXBElement<CityType> cityElement2 = new JAXBElement<CityType>(new QName("http://rolep.net", "City"), CityType.class, city);
        String str = JAXB_PARSER.marshal(cityElement);
        JAXB_PARSER.validate(str);
        System.out.println(str);
        String strCity =JAXB_PARSER.marshal(cityElement2);
        JAXB_PARSER.validate(strCity);
        System.out.println(strCity);
    }
}