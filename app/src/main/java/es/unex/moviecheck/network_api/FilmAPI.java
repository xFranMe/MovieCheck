package es.unex.moviecheck.network_api;

import es.unex.moviecheck.model.FilmsPages;
import es.unex.moviecheck.model.GenresList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmAPI {
    @GET("/3/movie/popular")
    Call<FilmsPages> getFilms(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("/3/genre/movie/list")
    Call<GenresList> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
