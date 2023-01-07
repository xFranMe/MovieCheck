package es.unex.moviecheck.sharedInterfaces;

import java.util.List;

import es.unex.moviecheck.model.Genre;

public interface OnGenresLoadedListener {
    void onGenresLoaded(List<Genre> genres);
}
