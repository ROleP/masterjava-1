package ru.javaops.web.handler;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import com.sun.xml.ws.api.handler.MessageHandlerContext;
import com.sun.xml.ws.api.message.Message;
import com.sun.xml.ws.api.streaming.XMLStreamWriterFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;

import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by rolep on 25/05/17.
 */
@Slf4j
public abstract class SoapLoggingHandler extends SoapBaseHandler {

    private final Level loggingLevel;

    protected SoapLoggingHandler(Level loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    private static final Map<Level, HANDLER> HANDLER_MAP = new EnumMap<Level, HANDLER>(Level.class) {
        {
            put(Level.TRACE, HANDLER.DEBUG);
            put(Level.DEBUG, HANDLER.DEBUG);
            put(Level.ERROR, HANDLER.ERROR);
            put(Level.WARN, HANDLER.ERROR);
            put(Level.INFO, HANDLER.INFO);
        }
    };

    protected enum HANDLER {
        NONE {
            @Override
            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
            }

            @Override
            public void handleFault(MessageHandlerContext context) {
            }
        },
        ERROR {
            private static final String REQUEST_MSG = "REQUEST_MSG";

            @Override
            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                if (isRequest) {
                    context.put(REQUEST_MSG, context.getMessage().copy());
                }
            }

            @Override
            public void handleFault(MessageHandlerContext context) {
                log.error("Fault SOAP request:\n" + getMessageText((Message) context.get(REQUEST_MSG)));
            }

        },
        INFO {
            @Override
            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                ERROR.handleMessage(context, isRequest);
                log.info((isRequest ? "SOAP request: " : "SOAP response: ") + context.getMessage());
            }

            @Override
            public void handleFault(MessageHandlerContext context) {
                ERROR.handleFault(context);
            }

        },
        DEBUG {
            @Override
            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                log.info((isRequest ? "SOAP request:\n" : "SOAP response:\n") + getMessageText(context.getMessage().copy()));
            }

            @Override
            public void handleFault(MessageHandlerContext context) {
                log.error("Fault SOAP message:\n" + getMessageText(context.getMessage().copy()));
            }

        };

        public abstract void handleMessage(MessageHandlerContext context, boolean isRequest);

        public abstract void handleFault(MessageHandlerContext context);

        protected static String getMessageText(Message msg) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                XMLStreamWriter writer = XMLStreamWriterFactory.createXMLStreamWriter(out, "UTF-8");
                IndentingXMLStreamWriter wrap = new IndentingXMLStreamWriter(writer);
                msg.writeTo(wrap);
                return out.toString(StandardCharsets.UTF_8.name());
            } catch (Exception e) {
                log.warn("Couldn't get SOAP message for logging", e);
                return null;
            }
        }
    }

    protected abstract boolean isRequest(boolean isOutbound);

    @Override
    public boolean handleMessage(MessageHandlerContext context) {
        HANDLER_MAP.get(loggingLevel).handleMessage(context, isRequest(isOutboud(context)));
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        HANDLER_MAP.get(loggingLevel).handleFault(context);
        return true;
    }
}
