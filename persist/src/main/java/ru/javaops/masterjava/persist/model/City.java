package ru.javaops.masterjava.persist.model;

import lombok.*;

/**
 * Created by rolep on 21/04/17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class City extends BaseEntity {

    private @NonNull
    String id;
    private @NonNull
    String name;
}
