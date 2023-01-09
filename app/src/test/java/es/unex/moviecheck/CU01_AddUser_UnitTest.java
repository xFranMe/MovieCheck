package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.User;

public class CU01_AddUser_UnitTest {

    protected User user;

    @Test
    public void shouldGetUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void shouldGetEmail() {
        assertEquals("user@email.es", user.getEmail());
    }

    @Test
    public void shouldGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void shouldSetUsername() {
        user.setUsername("user");
        assertEquals("user", user.getUsername());
    }

    @Test
    public void shouldSetEmail() {
        user.setEmail("user@unex.es");
        assertEquals("user@unex.es", user.getEmail());
    }

    @Test
    public void shouldSetPassword() {
        user.setPassword("pass");
        assertEquals("pass", user.getPassword());
    }

    @Before
    public void initTest() {
        user = new User ("username","user@email.es","password");
    }
}
