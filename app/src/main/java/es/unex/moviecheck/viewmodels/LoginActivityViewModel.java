package es.unex.moviecheck.viewmodels;

import androidx.lifecycle.ViewModel;

import es.unex.moviecheck.model.User;
import es.unex.moviecheck.repository.UsersRepository;

/**
 * {@link ViewModel} for {@link es.unex.moviecheck.activities.LoginActivity}
 */
public class LoginActivityViewModel extends ViewModel {

    private final UsersRepository usersRepository;

    public LoginActivityViewModel(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Comprueba si las credenciales introducidas corresponden a algún usuario dado de alta en la App (es decir, si su información está en la
     * BD). En caso afirmativo se devueve true. En caso contrario se devuelve false.
     */
    public boolean accountExists(String username, String password) {
        User user = usersRepository.getUsersByUsername().get(username);
        return user != null && user.getPassword().equals(password);
    }
}
