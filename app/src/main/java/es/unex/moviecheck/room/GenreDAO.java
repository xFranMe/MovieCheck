package es.unex.moviecheck.room;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.moviecheck.model.Genre;

@Dao
public interface GenreDAO {

    @Insert
    void insertGenre(Genre genre);

    @Delete
    void deleteGenre(Genre genre);

    @Insert(onConflict = IGNORE)
    void insertAllGenres(List<Genre> list);

    @Query("SELECT * FROM Genres")
    LiveData<List<Genre>> getAllGenres();
}
