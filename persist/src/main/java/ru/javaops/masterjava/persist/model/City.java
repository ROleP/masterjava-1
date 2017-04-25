package ru.javaops.masterjava.persist.model;

import lombok.*;

/**
 * Created by rolep on 24/04/17.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString(callSuper = true)
public class City extends BaseEntity {

    @NonNull
    private String ref;
    @NonNull
    private String name;

    public City(Integer id, String ref, String name) {
        this(ref, name);
        this.id = id;
    }
}
