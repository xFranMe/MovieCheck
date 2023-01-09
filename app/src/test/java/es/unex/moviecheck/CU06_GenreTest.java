package es.unex.moviecheck;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Genre;

public class CU06_GenreTest {
    protected Genre genre;

    @Test
    public void shouldGetId (){
        assertEquals(1, genre.getId().intValue());
    }

    @Test
    public void shouldGetName (){
        assertEquals("genre", genre.getName());
    }

    @Test
    public void shouldSetId (){
        genre.setId(2);
        assertEquals(2, genre.getId().intValue());
    }

    @Test
    public void shouldSetName (){
        genre.setName("name");
        assertEquals("name", genre.getName());
    }

    @Test
    public void shouldCompareTo(){
        Genre aux = new Genre(1,"genre");
        assertEquals(0, aux.compareTo(genre));
    }

    @Before
    public void initTest(){
        genre = new Genre(1,"genre");
    }

}
