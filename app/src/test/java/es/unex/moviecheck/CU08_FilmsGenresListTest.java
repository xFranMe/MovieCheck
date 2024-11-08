package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.FilmsGenresList;

public class CU08_FilmsGenresListTest {

    protected FilmsGenresList filmsGenresList;

    @Test
    public void shouldGetFilmId (){
        assertEquals(1, filmsGenresList.getFilmID());
    }

    @Test
    public void shouldGetGenreId (){
        assertEquals(1, filmsGenresList.getGenreID());
    }

    @Test
    public void shouldSetFilmId (){
        filmsGenresList.setFilmID(2);
        assertEquals(2, filmsGenresList.getFilmID());
    }

    @Test
    public void shouldSetGenreId (){
        filmsGenresList.setGenreID(2);
        assertEquals(2, filmsGenresList.getGenreID());
    }

    @Before
    public void initTest() {
        filmsGenresList = new FilmsGenresList(1,1);
    }
}
