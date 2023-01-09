package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.model.FilmsPages;

public class CU08_FilmsPagesTest {

    protected FilmsPages filmsPages;
    private List<Films> films;

    @Test
    public void shouldGetPage(){
        assertEquals(1, filmsPages.getPage().intValue());
    }

    @Test
    public void shouldGetTotalResults(){
        assertEquals(1, filmsPages.getTotalResults().intValue());
    }

    @Test
    public void shouldGetTotalPages(){
        assertEquals(1, filmsPages.getTotalPages().intValue());
    }

    @Test
    public void shouldGetResults(){
        assertEquals(films, filmsPages.getResults());
    }

    @Test
    public void shouldSetPage(){
        filmsPages.setPage(2);
        assertEquals(2, filmsPages.getPage().intValue());
    }

    @Test
    public void shouldSetTotalResults(){
        filmsPages.setTotalResults(2);
        assertEquals(2, filmsPages.getTotalResults().intValue());
    }

    @Test
    public void shouldSetTotalPages(){
        filmsPages.setTotalPages(2);
        assertEquals(2, filmsPages.getTotalPages().intValue());
    }

    @Test
    public void shouldSetResults(){
        List<Films> list = new ArrayList<>();
        filmsPages.setResults(list);
        assertEquals(list, filmsPages.getResults());
    }

    @Before
    public void initTest(){
        films = new ArrayList<>();
        filmsPages = new FilmsPages(1,1,1,films);
    }
}
