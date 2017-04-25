package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by rolep on 24/04/17.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserGroup {
    @NonNull
    @Column("user_id")
    private Integer userId;
    @NonNull
    @Column("group_id")
    private Integer groupId;
}
