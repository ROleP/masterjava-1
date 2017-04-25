package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

/**
 * Created by rolep on 24/04/17.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Group extends BaseEntity {
    @NonNull
    private String name;
    @NonNull
    private GroupType type;
    @NonNull
    @Column("project_id")
    private int projectId;

    public Group(Integer id, String name, GroupType type, int projectId) {
        this(name, type, projectId);
        this.id = id;
    }
}