package es.unex.moviecheck;

import android.content.Context;

import es.unex.moviecheck.networkAPI.FilmsNetworkDataSource;
import es.unex.moviecheck.repository.Repository;
import es.unex.moviecheck.repository.UsersRepository;
import es.unex.moviecheck.room.FilmsDatabase;
import es.unex.moviecheck.viewModels.ViewModelFactory;

public class AppContainer {
    private FilmsDatabase filmsDatabase;
    private FilmsNetworkDataSource filmsNetworkDataSource;
    public UsersRepository usersRepository;
    public Repository repository;
    public ViewModelFactory factory;

    public AppContainer(Context context){
        filmsDatabase = FilmsDatabase.getInstance(context);
        filmsNetworkDataSource = FilmsNetworkDataSource.getInstance();
        repository = Repository.getInstance(filmsDatabase.filmDAO(), filmsDatabase.favoritesDAO(), filmsDatabase.pendingsDAO(), filmsDatabase.commentDAO(), filmsDatabase.ratingDAO(), filmsDatabase.genreDAO(), filmsDatabase.filmsGenresListDAO(), filmsNetworkDataSource);
        usersRepository = UsersRepository.getInstance(filmsDatabase.userDAO());
        factory = new ViewModelFactory(repository, usersRepository);
    }
}
