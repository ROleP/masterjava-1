package ru.javaops.web.handler;

import org.slf4j.event.Level;

/**
 * Created by rolep on 25/05/17.
 */
public class SoapClientLoggingHandler extends SoapLoggingHandler {

    public SoapClientLoggingHandler(Level loggingLevel) {
        super(loggingLevel);
    }

    @Override
    protected boolean isRequest(boolean isOutbound) {
        return isOutbound;
    }
}
