package es.unex.moviecheck.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.moviecheck.model.Comments;

@Dao
public interface CommentDAO {

    @Query("SELECT * FROM Comments WHERE filmID = (:filmID)")
    LiveData<List<Comments>> getFilmComments(int filmID);

    @Insert
    void addComment(Comments comments);

    @Delete
    void deleteComment(Comments comment);
}
