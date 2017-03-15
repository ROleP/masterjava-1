package net.rolep.jpractice.threads;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rolep on 3/15/17.
 */
public final class DateAdapterThreadLocal extends XmlAdapter<String, Date> {

    private static final ThreadLocal<DateFormat> THREAD_CACHE = new ThreadLocal<>();

    @Override
    public Date unmarshal(String v) throws Exception {
        if (v == null || v.equals("")) {
            return null;
        }
        return getFormat().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return getFormat().format(v);
    }

    private static DateFormat getFormat() {
        DateFormat format = THREAD_CACHE.get();
        if (format == null) {
            format = new SimpleDateFormat("dd.MM.yyyy");
            THREAD_CACHE.set(format);
        }
        return format;
    }
}
