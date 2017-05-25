package ru.javaops.masterjava.service.mail.util;

import lombok.AllArgsConstructor;
import org.apache.commons.io.input.CloseShieldInputStream;
import ru.javaops.masterjava.service.mail.Attach;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by rolep on 16/05/17.
 */
public class Attachments {
    public static Attach getAttach(String name, InputStream inputStream) {
       return new Attach(name, new DataHandler(new InpustStreamDataSource(inputStream)));
    }

    @AllArgsConstructor
    private static class InpustStreamDataSource implements DataSource {
        private InputStream inputStream;

        @Override
        public InputStream getInputStream() throws IOException {
//            if (inputStream == null) {
//                throw new IOException("Second getInputStream() call is not supported");
//            }
//            InputStream res = inputStream;
//            inputStream = null;
//            return res;
            return new CloseShieldInputStream(inputStream);
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public String getContentType() {
            return "application/octet-stream";
        }

        @Override
        public String getName() {
            return "";
        }
    }
}
