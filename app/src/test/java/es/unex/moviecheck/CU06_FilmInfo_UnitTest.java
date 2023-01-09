package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.moviecheck.model.Films;

public class CU06_FilmInfo_UnitTest {

    protected Films films;
    protected List<Integer> genresids;

    @Test
    public void shouldGetAdult (){
        assertEquals(true, films.getAdult());
    }

    @Test
    public void shouldGetBackdropPath (){
        assertEquals("bk_poster", films.getBackdropPath());
    }

    @Test
    public void shouldGetOriginalLanguage (){
        assertEquals("language", films.getOriginalLanguage());
    }

    @Test
    public void shouldGetPopularity (){
        assertEquals(0.0, films.getPopularity(),0.00000001);
    }

    @Test
    public void shouldGetOriginalTitle (){
        assertEquals("originalTitle", films.getOriginalTitle());
    }

    @Test
    public void shouldGetVideo (){
        assertEquals(false, films.getVideo());
    }

    @Test
    public void shouldGetVoteCount (){
        assertEquals(0, films.getVoteCount().intValue());
    }

    @Test
    public void shouldGetId (){
        assertEquals(0, films.getId().intValue());
    }

    @Test
    public void shouldGetTitle (){
        assertEquals("title", films.getTitle());
    }

    @Test
    public void shouldGetGenresIds (){
        assertEquals(genresids, films.getGenreIds());
    }

    @Test
    public void shouldGetPosterPath (){
        assertEquals("poster", films.getPosterPath());
    }

    @Test
    public void shouldGetOverview (){
        assertEquals("overview", films.getOverview());
    }

    @Test
    public void shouldGetRealeaseDate (){
        assertEquals("date", films.getReleaseDate());
    }

    @Test
    public void shouldGetVoteAverage (){
        assertEquals(0.0, films.getVoteAverage(),0.00000001);
    }

    @Test
    public void shouldGetTotalVotesMovieCheck (){
        assertEquals(0, films.getTotalVotesMovieCheck().intValue());
    }

    @Test
    public void shouldGetTotalRatingMovieCheck (){
        assertEquals(0, films.getTotalRatingMovieCheck().intValue());
    }

    @Test
    public void shouldSetAdult (){
        films.setAdult(false);
        assertEquals(false, films.getAdult());
    }

    @Test
    public void shouldSetBackdropPath (){
        films.setBackdropPath("bk_path");
        assertEquals("bk_path", films.getBackdropPath());
    }

    @Test
    public void shouldSetOriginalLanguage (){
        films.setOriginalLanguage("og_language");
        assertEquals("og_language", films.getOriginalLanguage());
    }

    @Test
    public void shouldSetPopularity (){
        films.setPopularity(1.1);
        assertEquals(1.1, films.getPopularity(),0.00000001);
    }

    @Test
    public void shouldSetOriginalTitle (){
        films.setOriginalTitle("og_title");
        assertEquals("og_title", films.getOriginalTitle());
    }

    @Test
    public void shouldSetVideo (){
        films.setAdult(true);
        assertEquals(true, films.getAdult());
    }

    @Test
    public void shouldSetVoteCount (){
        films.setVoteCount(1);
        assertEquals(1, films.getVoteCount().intValue());
    }

    @Test
    public void shouldSetId (){
        films.setId(1);
        assertEquals(1, films.getId().intValue());
    }

    @Test
    public void shouldSetTitle (){
        films.setTitle("f_title");
        assertEquals("f_title", films.getTitle());
    }

    @Test
    public void shouldSetGenresIds (){
        List<Integer> list = new ArrayList<>();
        films.setGenreIds(list);
        assertEquals(genresids, films.getGenreIds());
    }

    @Test
    public void shouldSetPosterPath (){
        films.setPosterPath("path");
        assertEquals("path", films.getPosterPath());
    }

    @Test
    public void shouldSetOverview (){
        films.setOverview("f_overview");
        assertEquals("f_overview", films.getOverview());
    }

    @Test
    public void shouldSetRealeaseDate (){
        films.setReleaseDate("f_date");
        assertEquals("f_date", films.getReleaseDate());
    }

    @Test
    public void shouldSetVoteAverage (){
        films.setVoteAverage(1.1);
        assertEquals(1.1, films.getVoteAverage(),0.00000001);
    }

    @Test
    public void shouldSetTotalVotesMovieCheck (){
        films.setTotalVotesMovieCheck(1);
        assertEquals(1, films.getTotalVotesMovieCheck().intValue());
    }

    @Test
    public void shouldSetTotalRatingMovieCheck (){
        films.setTotalRatingMovieCheck(1);
        assertEquals(1, films.getTotalRatingMovieCheck().intValue());
    }

    @Before
    public void initTest(){
        genresids = new ArrayList<>();
        films = new Films(true, "bk_poster", "language", 0.0,"originalTitle",false,0,0,
                "title",genresids,"poster","overview","date",0.0,0,0);
    }
}
