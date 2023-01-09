package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Rating;

public class CU13_AddRating_UnitTest {

    protected Rating rating;

    @Test
    public void shouldGetFilmId(){
        assertEquals(1, rating.getFilmID());
    }

    @Test
    public void shouldGetUsername(){
        assertEquals("username", rating.getUsername());
    }

    @Test
    public void shouldGetRating(){
        assertEquals(1, rating.getRatingValue());
    }

    @Test
    public void shouldSetFilmId(){
        rating.setFilmID(2);
        assertEquals(2, rating.getFilmID());
    }

    @Test
    public void shouldSetUsername(){
        rating.setUsername("user");
        assertEquals("user", rating.getUsername());
    }

    @Test
    public void shouldSetRating(){
        rating.setRatingValue(2);
        assertEquals(2, rating.getRatingValue());
    }

    @Before
    public void initTest(){
        rating = new Rating(1,"username",1);
    }
}
