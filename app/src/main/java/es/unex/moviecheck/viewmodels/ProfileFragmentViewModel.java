package es.unex.moviecheck.viewmodels;

import androidx.lifecycle.ViewModel;

import es.unex.moviecheck.model.User;
import es.unex.moviecheck.repository.UsersRepository;

public class ProfileFragmentViewModel extends ViewModel {

    private final UsersRepository usersRepository;

    public ProfileFragmentViewModel(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUser(String username) {
        return usersRepository.getUser(username);
    }
}
