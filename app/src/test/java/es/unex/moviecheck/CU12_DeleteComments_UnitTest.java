package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Comments;

public class CU12_DeleteComments_UnitTest {

    protected Comments comments;

    @Test
    public void shouldGetUsername (){
        assertEquals("username", comments.getUsername());
    }

    @Test
    public void shouldGetFilmId (){
        assertEquals(1, comments.getFilmID());
    }

    @Test
    public void shouldSetUsername (){
        comments.setUsername("user");
        assertEquals("user", comments.getUsername());
    }

    @Test
    public void shouldSetFilmId (){
        comments.setFilmID(2);
        assertEquals(2, comments.getFilmID());
    }

    @Before
    public void initTest(){
        comments = new Comments(1,"username",1,"text");
    }

}
