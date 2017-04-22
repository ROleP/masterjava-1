package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

/**
 * Created by rolep on 21/04/17.
 */
@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class CityDao implements AbstractDao {

    @SqlQuery("SELECT * FROM cities ORDER BY name ASC ")
    public abstract List<City> getAllCities();

    @SqlQuery("SELECT * FROM cities WHERE id = :id ")
    public abstract City getCityById(@Bind String id);

    @SqlQuery("SELECT * FROM cities where name like '%:name%' ORDER BY name ASC ")
    public abstract List<City> getCitiesByName(@Bind String name);

    @SqlUpdate("INSERT INTO cities values ")
    abstract void insert(@BindBean City city);

    @Override
    @SqlUpdate("TRUNCATE cities")
    public abstract void clean();
}
