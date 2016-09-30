package com.knoldus.service;

import com.knoldus.models.User;
import com.knoldus.service.impl.UserServiceImpl;
import javaslang.collection.List;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by harmeet on 30/9/16.
 */
public class UserServiceImplTest {
    
    private UserService userService;
    
    @Before
    public void init() {
        userService = new UserServiceImpl();
    }
    
    @Test
    public void testFindAllUsersByQuery() throws Exception {
        List<User> users = userService.findAllUsersByQuery("SELECT name FROM users").get(10, TimeUnit.SECONDS);
        List<User> expected = List.of("knoldus", "scala", "java", "champ", "lightbend", "partner")
                .map(name -> new User(name));

        assertEquals(users, expected);
    }

    @Test(expected = Exception.class)
    public void testFindAllUsersByQueryException() throws Exception {
        userService.findAllUsersByQuery("SELECT id FROM users").get(10, TimeUnit.SECONDS);
    }
}
