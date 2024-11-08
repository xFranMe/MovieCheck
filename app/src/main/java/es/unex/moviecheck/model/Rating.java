package es.unex.moviecheck.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// Clase valoración
@Entity(tableName = "Ratings",primaryKeys = {"filmID","username"},foreignKeys = {@ForeignKey(entity = Films.class, parentColumns = "filmID", childColumns = "filmID", onDelete = ForeignKey.CASCADE),
                                                                                @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "username", onDelete = ForeignKey.CASCADE)})
public class Rating {

    // Atributos clase valoración
    @ColumnInfo(name="filmID")
    private int filmID;
    @NonNull
    @ColumnInfo(name="username")
    private String username;
    private int ratingValue;

    public Rating(int filmID, @NonNull String username, int ratingValue){
        this.filmID = filmID;
        this.username = username;
        this.ratingValue = ratingValue;
    }

    // Métodos SET and GET de valoración
    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
}
