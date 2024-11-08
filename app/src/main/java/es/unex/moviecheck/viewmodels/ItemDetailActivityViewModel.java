package es.unex.moviecheck.viewmodels;

import androidx.lifecycle.ViewModel;

import es.unex.moviecheck.model.Comments;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.repository.Repository;

public class ItemDetailActivityViewModel extends ViewModel {
    private final Repository repository;

    public ItemDetailActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getFilm(Films film) {
        repository.getFilm(film);
    }

    public void deleteComment(Comments comment) {
        repository.deleteComment(comment);
    }
}
