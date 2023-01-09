package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Pendings;

public class CU14_AddPendings_UnitTest {

    protected Pendings pendings;

    @Test
    public void shouldGetFilmId(){
        assertEquals(1, pendings.getFilmID());
    }

    @Test
    public void shouldGetUsername(){
        assertEquals("username", pendings.getUsername());
    }

    @Test
    public void shouldSetFilmId(){
        pendings.setFilmID(2);
        assertEquals(2, pendings.getFilmID());
    }

    @Test
    public void shouldSetUsername(){
        pendings.setUsername("user");
        assertEquals("user", pendings.getUsername());
    }

    @Before
    public void initTest(){
        pendings = new Pendings(1,"username");
    }
}
