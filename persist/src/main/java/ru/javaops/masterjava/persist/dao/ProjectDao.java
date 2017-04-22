package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.GroupProjectType;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

/**
 * Created by rolep on 21/04/17.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class ProjectDao implements AbstractDao {

    @SqlQuery("SELECT * FROM projects ORDER BY name ASC ")
    public abstract List<Project> getAllProjects();

    @SqlQuery("SELECT * FROM projects WHERE type = CAST (:type AS GROUP_PROJECT_TYPE) ORDER BY name ASC ")
    public abstract List<Project> getProjectsByType(@BindBean GroupProjectType type);

    @SqlQuery("SELECT * FROM projects where name like '%:name%' ORDER BY name ASC ")
    public abstract List<Project> getProjectsByName(@Bind String name);

    @SqlUpdate("INSERT INTO projects (name, type) VALUES (:name, CAST (:type AS GROUP_PROJECT_TYPE)) ")
    abstract void insertProject(@BindBean Project project);

    @Override
    @SqlUpdate("TRUNCATE projects")
    public abstract void clean();
}
