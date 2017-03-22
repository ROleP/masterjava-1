package net.rolep.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by rolep on 22/03/17.
 */
public class XsltProcessorTest {
    @Test
    public void transform() throws Exception {
        try (InputStream xslInputStream = Resources.getResource(JaxbParserTest.class, "/myCities.xsl").openStream();
            InputStream xmlInputStrean = Resources.getResource(JaxbParserTest.class, "/myPayload.xml").openStream()) {
            XsltProcessor processor = new XsltProcessor(xslInputStream);
            System.out.println(processor.transform(xmlInputStrean));
        }

    }
}