package es.unex.moviecheck.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.Collection;

import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.repository.Repository;

public class FavoritesFragmentViewModel extends ViewModel {

    private final Repository repository;

    public FavoritesFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public Collection<Films> getUserFavoritesFilms() {
        return repository.getUserFavoritesFilms().values();
    }
}
