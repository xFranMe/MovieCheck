package es.unex.moviecheck.sharedInterfaces;

import java.util.List;

import es.unex.moviecheck.model.Films;

public interface OnFilmsLoadedListener {
    void onFilmsLoaded(List<Films> films);
}
