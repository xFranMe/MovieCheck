package es.unex.moviecheck;

import android.content.Context;

import es.unex.moviecheck.network_api.FilmsNetworkDataSource;
import es.unex.moviecheck.repository.Repository;
import es.unex.moviecheck.repository.UsersRepository;
import es.unex.moviecheck.room.FilmsDatabase;
import es.unex.moviecheck.viewmodels.ViewModelFactory;

public class AppContainer {
    private final FilmsDatabase filmsDatabase;
    private final FilmsNetworkDataSource filmsNetworkDataSource;
    public final UsersRepository usersRepository;
    public final Repository repository;
    public final ViewModelFactory factory;

    public AppContainer(Context context){
        filmsDatabase = FilmsDatabase.getInstance(context);
        filmsNetworkDataSource = FilmsNetworkDataSource.getInstance();
        repository = Repository.getInstance(filmsDatabase.filmDAO(), filmsDatabase.favoritesDAO(), filmsDatabase.pendingsDAO(), filmsDatabase.commentDAO(), filmsDatabase.ratingDAO(), filmsDatabase.genreDAO(), filmsDatabase.filmsGenresListDAO(), filmsNetworkDataSource);
        usersRepository = UsersRepository.getInstance(filmsDatabase.userDAO());
        factory = new ViewModelFactory(repository, usersRepository);
    }
}
