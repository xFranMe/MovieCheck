package es.unex.moviecheck.network_api;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import es.unex.moviecheck.model.Genre;
import es.unex.moviecheck.model.GenresList;
import es.unex.moviecheck.shared_interfaces.OnGenresLoadedListener;
import es.unex.moviecheck.support.AppExecutors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenresNetworkLoaderRunnable implements Runnable{

    private final OnGenresLoadedListener onGenresLoadedListener;

    // Campos necesarios para trabajar con la API
    private static final String URLBASE = "https://api.themoviedb.org";
    private static final String APIKEY = "a99f635c038e47e61a70f3ab2b526e3e";
    private static final String LANGUAGE = "es-ES";
    private static final String TAG = "APP";

    public GenresNetworkLoaderRunnable(OnGenresLoadedListener onGenresLoadedListener){
        this.onGenresLoadedListener = onGenresLoadedListener;
    }

    @Override
    public void run(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FilmAPI api = retrofit.create(FilmAPI.class);
        Call<GenresList> call = api.getGenres(APIKEY,LANGUAGE);

        call.enqueue(new Callback<GenresList>() {
            @Override
            public void onResponse(@NonNull Call<GenresList> call, @NonNull Response<GenresList> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"CODE: "+response.code());
                }

                assert response.body() != null;
                List<Genre> genres = response.body().getGenres();
                AppExecutors.getInstance().mainThread().execute(() -> onGenresLoadedListener.onGenresLoaded(genres));
            }

            @Override
            public void onFailure(@NonNull Call<GenresList> call, @NonNull Throwable t) {
                Log.i(TAG,"ERROR: LA APLICACIÓN FALLÓ AL REALIZAR LA CONSULTA");
            }
        });
    }
}
