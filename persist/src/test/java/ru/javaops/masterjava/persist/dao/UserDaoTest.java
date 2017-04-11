package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.persist.UserTestData;
import ru.javaops.masterjava.persist.model.User;

import java.util.List;

import static ru.javaops.masterjava.persist.UserTestData.*;

/**
 * Created by rolep on 11/04/17.
 */
public class UserDaoTest extends AbstractDaoTest<UserDao> {

    public UserDaoTest() {
        super(UserDao.class);
    }

    @BeforeClass
    public static void init() {
        UserTestData.init();
    }

    @Before
    public static void setUp(){
        UserTestData.setUp();
    }

    @Test
    public void getWithLimit() {
        List<User> users = dao.getWithLimit(5);
        Assert.assertEquals(FIRST5_USERS, users);
    }
}
