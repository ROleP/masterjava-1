package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Created by rolep on 10/05/17.
 */
@AllArgsConstructor
public class GroupResult {
    private final int success;
    private final List<MailResult> failed;
    private final String failedCause;

    @Override
    public String toString() {
        return "Success: " + success + '\n' +
                "Failed: " + failed.toString() + '\n' +
                (failedCause == null ? "" : "Failed cause" + failedCause);
    }
}
