package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.moviecheck.model.Genre;
import es.unex.moviecheck.model.GenresList;

public class CU08_GenreListTest {

    protected GenresList genresList;

    protected List<Genre> list;

    @Test
    public void shouldGetGenres(){
        assertEquals(list, genresList.getGenres());
    }

    @Test
    public void shouldSetGenres(){
        List<Genre> genreslist = new ArrayList<>();
        genresList.setGenres(genreslist);
        assertEquals(genreslist, genresList.getGenres());
    }

    @Before
    public void initTest(){
        list = new ArrayList<>();
        genresList = new GenresList(list);
    }
}
