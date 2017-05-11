package ru.javaops.web;

import com.typesafe.config.Config;
import ru.javaops.masterjava.config.Configs;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Map;

/**
 * Created by rolep on 09/05/17.
 */
public class WsClient<T> {
    private static Config HOSTS;

    private final Class<T> serviceClass;
    private final Service service;

    private String endpointAddress;

    static {
        HOSTS = Configs.getConfig("hosts.conf", "hosts");
    }

    public WsClient(URL wsdUrl, QName qName, Class<T> serviceClass) {
        this.serviceClass = serviceClass;
        this.service = Service.create(wsdUrl, qName);
    }

    public void init(String host, String endpointAddress) {
        this.endpointAddress = HOSTS.getString(host) + endpointAddress;
    }

    public T getPort() {
        T port = service.getPort(serviceClass);
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> requestContext = bp.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
        return port;
    }

}