package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rolep on 27/04/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addressee {
    private String name;
    private String email;
}
