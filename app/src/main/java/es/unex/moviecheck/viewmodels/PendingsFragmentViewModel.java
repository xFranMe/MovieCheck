package es.unex.moviecheck.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.Collection;

import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.repository.Repository;

public class PendingsFragmentViewModel extends ViewModel {

    private final Repository repository;

    public PendingsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public Collection<Films> getUserPendingFilms() {
        return repository.getUserPendingsFilms().values();
    }

}
