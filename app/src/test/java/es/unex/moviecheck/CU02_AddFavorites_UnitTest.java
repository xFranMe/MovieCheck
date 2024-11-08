package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Favorites;

public class CU02_AddFavorites_UnitTest {

    protected Favorites favorites;

    @Test
    public void shouldGetFilmId(){
        assertEquals(1, favorites.getFilmID());
    }

    @Test
    public void shouldGetUsername(){
        assertEquals("username", favorites.getUsername());
    }

    @Test
    public void shouldSetFilmId(){
        favorites.setFilmID(2);
        assertEquals(2, favorites.getFilmID());
    }

    @Test
    public void shouldSetUsername(){
        favorites.setUsername("user");
        assertEquals("user", favorites.getUsername());
    }

    @Before
    public void initTest(){
        favorites = new Favorites(1,"username");
    }
}
