package ru.javaops.masterjava.persist.model;

import lombok.*;

/**
 * Created by rolep on 21/04/17.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {
    private @NonNull
    String name;
    private @NonNull
    Project project;
}
