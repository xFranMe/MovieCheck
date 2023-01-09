package es.unex.moviecheck.shared_interfaces;

import java.util.List;

import es.unex.moviecheck.model.Genre;

public interface OnGenresLoadedListener {
    void onGenresLoaded(List<Genre> genres);
}
