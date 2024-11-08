package es.unex.moviecheck;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.moviecheck.model.Comments;

public class CU03_AddComments_UnitTest {

    protected Comments comments;

    @Test
    public void shouldGetCommentId (){
        assertEquals(1, comments.getCommentID());
    }

    @Test
    public void shouldGetUsername (){
        assertEquals("username", comments.getUsername());
    }

    @Test
    public void shouldGetFilmId (){
        assertEquals(1, comments.getFilmID());
    }

    @Test
    public void shouldGetText (){
        assertEquals("text", comments.getText());
    }

    @Test
    public void shouldSetCommentId  (){
        comments.setCommentID(2);
        assertEquals(2, comments.getCommentID());
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

    @Test
    public void shouldSetText (){
        comments.setText("comment");
        assertEquals("comment", comments.getText());
    }

    @Before
    public void initTest(){
        comments = new Comments(1,"username",1,"text");
    }

}
