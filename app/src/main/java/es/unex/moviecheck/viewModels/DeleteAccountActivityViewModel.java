package es.unex.moviecheck.viewModels;

import androidx.lifecycle.ViewModel;

import es.unex.moviecheck.repository.UsersRepository;

public class DeleteAccountActivityViewModel extends ViewModel {

    private final UsersRepository usersRepository;

    public DeleteAccountActivityViewModel(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void deleteAccount(String username) {
        usersRepository.deleteUser(username);
    }
}
