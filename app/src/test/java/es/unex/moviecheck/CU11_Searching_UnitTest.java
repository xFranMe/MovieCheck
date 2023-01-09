package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.unex.moviecheck.support.LevenshteinSearch;

public class CU11_Searching_UnitTest {

    protected  String a;
    protected  String b;
    protected  int distance;

    @Test
    public void sameCharactersTest () {
       a = "hola";
       b = "hola";

       distance = LevenshteinSearch.distance(a,b);
       assertEquals(0, distance);
    }

    @Test
    public void differentCharactersTest () {
        a = "hola";
        b = "menu";

        distance = LevenshteinSearch.distance(a,b);
        assertEquals(4, distance);
    }

    @Test
    public void similarCharactersTest () {
        a = "hola";
        b = "ola";

        distance = LevenshteinSearch.distance(a,b);
        assertEquals(1, distance);
    }
}
