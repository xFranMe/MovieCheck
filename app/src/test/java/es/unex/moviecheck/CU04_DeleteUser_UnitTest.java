package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.User;

public class CU04_DeleteUser_UnitTest {

    protected User user;

    @Test
    public void shouldGetUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void shouldSetUsername() {
        user.setUsername("user");
        assertEquals("user", user.getUsername());
    }

    @Before
    public void initTest() {
        user = new User ("username","user@email.es","password");
    }
}
