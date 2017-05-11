package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by rolep on 10/05/17.
 */
@AllArgsConstructor
@NoArgsConstructor
public class GroupResult {
    private int success;
    private List<MailResult> failed;
    private String failedCause;

    @Override
    public String toString() {
        return "Success: " + success + '\n' +
                "Failed: " + failed.toString() + '\n' +
                (failedCause == null ? "" : "Failed cause" + failedCause);
    }
}
