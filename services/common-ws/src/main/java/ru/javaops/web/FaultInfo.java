package ru.javaops.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.javaops.masterjava.ExceptionType;

/**
 * Created by rolep on 15/05/17.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FaultInfo {
    private @NonNull
    ExceptionType type;

    @Override
    public String toString() {
        return type.toString();
    }
}
