package es.unex.moviecheck.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import es.unex.moviecheck.AppContainer;
import es.unex.moviecheck.MyApplication;
import es.unex.moviecheck.adapters.FilmAdapter;
import es.unex.moviecheck.adapters.FilmListAdapter;
import es.unex.moviecheck.fragments.ProfileFragment;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.viewmodels.HomeActivityViewModel;
import es.unex.moviecheck.R;
import es.unex.moviecheck.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity implements ProfileFragment.ProfileListener, FilmAdapter.FilmListener, FilmListAdapter.ActionButtonListener {

    // Referencia al ViewModel
    HomeActivityViewModel homeActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Para el ViewModel
        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        homeActivityViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factory).get(HomeActivityViewModel.class);

        homeActivityViewModel.getUserFilmData(getSharedPreferences(getPackageName()+"_preferences", Context.MODE_PRIVATE).getString("USERNAME", ""));

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Passing each menu ID as a set of Ids because each
        //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_explore, R.id.navigation_favorites, R.id.navigation_pendings, R.id.navigation_profile)
                .build();

        // Se centra la etiqueta (título) de la App Bar para cada pantalla
        centerAppBarTitle();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * Centra el título de la AppBar de cada una de las secciones principales de la App.
     */
    private void centerAppBarTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if(!textViews.isEmpty()) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                appCompatTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }
    }

    /**
     * Implementación del método onInfoButtonPressed() de la interfaz ProfileListener definido para controlar el comportamiento del botón
     * de 'Información de la App' localizado en ProfileFragment.
     * Se lanza la actividad AppInfoActivity para mostrar información de interés de la App.
     */
    @Override
    public void onInfoButtonPressed() {
        Intent intent = new Intent(this, AppInfoActivity.class);
        startActivity(intent);
    }

    /**
     * Implementación del método onLogoutButtonPressed() de la interfaz ProfileListener definido para controlar el comportamiento del botón
     * para cerrar sesión localizado en ProfileFragment.
     * Se lanza la actividad LoginActivity para volver a iniciar sesión y se finaliza la actividad actual para 'limpiar' la pila de Back.
     */
    @Override
    public void onLogoutButtonPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Implementación del método onDeleteAccountButtonPressed() de la interfaz ProfileListener definido para controlar el comportamiento del botón
     * para eliminar la cuenta de usuario en ProfileFragment.
     * Se lanza la actividad DeleteAccountActivity a la espera de si el usuario confirma la eliminación de la cuenta o no.
     * La respuesta es recogida en deleteAccountLauncher.
     */
    @Override
    public void onDeleteAccountButtonPressed() {
        Intent intent = new Intent(this, DeleteAccountActivity.class);
        deleteAccountLauncher.launch(intent);
    }

    /**
     * Recupera la respuesta devuelta por DeleteAccountActivity. Si el usuario no ha eliminado la cuenta, no se hace nada. En caso contrario
     * (RESULT_OK), se inicia la actividad de LoginActivity para acceder con una nueva cuenta y se finaliza la actividad actual para vaciar
     * la pila de Back.
     */
    ActivityResultLauncher<Intent> deleteAccountLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

    /**
     *
     * @param film Objeto Films que contiene la información de la película de la que se quiere mostrar la información
     */
    @Override
    public void onFilmSelected(Films film){
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }

    @Override
    public void onFavButtonPressed(Films film, FilmListAdapter filmListAdapter) {
        homeActivityViewModel.removeUserFavoriteFilm(film);
        runOnUiThread(() -> filmListAdapter.swap(new ArrayList<>(homeActivityViewModel.getUserFavoriteFilms())));
    }

    @Override
    public void onPendingButtonPressed(Films film, FilmListAdapter filmListAdapter) {
        homeActivityViewModel.removeUserPendingFilm(film);
        runOnUiThread(() -> filmListAdapter.swap(new ArrayList<>(homeActivityViewModel.getUserPendingFilms())));
    }
}