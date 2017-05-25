package ru.javaops.web.handler;

import org.slf4j.event.Level;

/**
 * Created by rolep on 25/05/17.
 */
public class SoapServerLoggingHandler extends SoapLoggingHandler {

    public SoapServerLoggingHandler() {
        super(Level.INFO);
    }

    @Override
    protected boolean isRequest(boolean isOutbound) {
        return !isOutbound;
    }
}
