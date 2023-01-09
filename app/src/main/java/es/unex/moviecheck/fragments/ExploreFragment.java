package es.unex.moviecheck.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unex.moviecheck.AppContainer;
import es.unex.moviecheck.MyApplication;
import es.unex.moviecheck.adapters.FilmAdapter;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.model.Genre;
import es.unex.moviecheck.shared_interfaces.OnFilmsLoadedListener;
import es.unex.moviecheck.shared_interfaces.OnGenresLoadedListener;
import es.unex.moviecheck.viewmodels.ExploreFragmentViewModel;
import es.unex.moviecheck.R;

public class ExploreFragment extends Fragment implements OnFilmsLoadedListener, OnGenresLoadedListener {

    // Referencias a vistas
    private SearchView svSearchFilm;
    private ImageButton ibResetFilms;
    private ChipGroup cgGenreFilter;

    // Adaptador para las películas de la RecyclerView
    private FilmAdapter filmAdapter;

    // Referencia al ViewModel
    ExploreFragmentViewModel exploreFragmentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        getViewReferences(view);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_explore);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        filmAdapter = new FilmAdapter(new ArrayList<>(), R.layout.explore_item_grid_content, getContext());
        recyclerView.setAdapter(filmAdapter);

        // Para el ViewModel
        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        exploreFragmentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factory).get(ExploreFragmentViewModel.class);

        exploreFragmentViewModel.getFilms().observe(getActivity(), this::onFilmsLoaded);
        exploreFragmentViewModel.getGenres().observe(getActivity(), this::onGenresLoaded);
        exploreFragmentViewModel.getFilmsByGenre().observe(getActivity(), this::onFilmsLoaded);
        exploreFragmentViewModel.getTitleFilterLiveData().observe(getActivity(), this::onFilmsLoaded);

        svSearchFilm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                exploreFragmentViewModel.getFilmsByTitle(query);
                return false;
            }
        });

        ibResetFilms.setOnClickListener(view1 -> exploreFragmentViewModel.forceFetchFilmAndGenres());

        return view;
    }

    private void getViewReferences(View view) {
        svSearchFilm = view.findViewById(R.id.svSearchFilm);
        ibResetFilms = view.findViewById(R.id.ibResetFilms);
        cgGenreFilter = view.findViewById(R.id.cgGenreFilter);
    }

    private void loadChipFilters(List<Genre> genres) {
        for (Genre genre : genres) {
            Chip chip = new Chip(getContext());
            chip.setText(genre.getName());
            chip.setChipBackgroundColorResource(R.color.gray);
            chip.setCloseIconVisible(false);
            cgGenreFilter.addView(chip);
            chip.setOnClickListener(view -> loadGenreFilms(genre.getId()));
        }
    }

    // Llamado al buscar por filtro
    private void loadGenreFilms(int genreId) {
        filmAdapter.clear();
        exploreFragmentViewModel.setGenre(genreId);
    }

    @Override
    public void onFilmsLoaded(List<Films> films) {
        Log.d("Repository", "Pelis actualizadas");
        getActivity().runOnUiThread(() -> filmAdapter.swap(films));
    }

    @Override
    public void onGenresLoaded(List<Genre> genres) {
        Collections.sort(genres);
        getActivity().runOnUiThread(() -> loadChipFilters(genres));
    }

}