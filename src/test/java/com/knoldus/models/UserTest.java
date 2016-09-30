package com.knoldus.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by harmeet on 30/9/16.
 */
public class UserTest {

    private User user1, user2, user3;

    @Before
    public void init(){
        user1 = new User("knoldus");
        user2 = new User("knoldus");
        user3 = new User("james");
    }

    @Test
    public void testHashCode(){
        assertThat(user1.hashCode(), is(equalTo(user2.hashCode())));
        assertThat(user1.hashCode(), is(not(equalTo(user3.hashCode()))));
        assertThat(user2.hashCode(), is(not(equalTo(user3.hashCode()))));
    }

    @Test
    public void testEqualsPositive(){
        assertThat(user1, equalTo(user2));
        assertThat(user2, is(not(equalTo(user3))));
        assertThat(user1, is(not(equalTo(user3))));
    }

    @Test
    public void testEqualsNegative(){
        assertThat(user1.equals(user1), is(true));

        User user4 = null;
        assertThat(user1.equals(user4), is(false));

        Object obj = new Object();
        assertThat(user1.equals(obj), is(false));
    }
}
