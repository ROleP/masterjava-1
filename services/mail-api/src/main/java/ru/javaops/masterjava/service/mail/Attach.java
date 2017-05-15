package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;

/**
 * Created by rolep on 15/05/17.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Attach {
    protected String name;

    @XmlMimeType("application/octet-stream")
    private DataHandler dataHandler;
}
