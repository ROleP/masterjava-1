package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javaops.masterjava.persist.CityTestData;
import ru.javaops.masterjava.persist.model.City;

import java.util.Map;

/**
 * Created by rolep on 25/04/17.
 */
public class CityDaoTest extends AbstractDaoTest<CityDao> {

    public CityDaoTest() {
        super(CityDao.class);
    }

    @Before
    public void setUp() throws Exception {
        CityTestData.setUp();
    }

    @Test
    public void getAll() throws Exception {
        final Map<String, City> cities = dao.getAsMap();
        Assert.assertEquals(CityTestData.CITIES, cities);
        System.out.println(cities.values());
    }
}
