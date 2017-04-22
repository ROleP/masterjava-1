package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column("full_name")
    private @NonNull
    String fullName;
    private @NonNull
    String email;
    private @NonNull
    UserFlag flag;
    private @NonNull
    City city;
    private List<Group> groups = new ArrayList<>();

    public User(Integer id, String fullName, String email, UserFlag flag) {
        this(fullName, email, flag);
        this.id = id;
    }
}