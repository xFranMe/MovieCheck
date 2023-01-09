package es.unex.moviecheck.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.moviecheck.AppContainer;
import es.unex.moviecheck.MyApplication;
import es.unex.moviecheck.R;
import es.unex.moviecheck.adapters.FilmListAdapter;
import es.unex.moviecheck.viewmodels.PendingsFragmentViewModel;

public class PendingsFragment extends Fragment {

    private FilmListAdapter filmListAdapter;

    // Referencia al ViewModel
    PendingsFragmentViewModel pendingsFragmentViewModel;


    public PendingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Para el ViewModel
        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        pendingsFragmentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factory).get(PendingsFragmentViewModel.class);

        View v = inflater.inflate(R.layout.fragment_pendings, container, false);
        filmListAdapter = new FilmListAdapter(new ArrayList<>(pendingsFragmentViewModel.getUserPendingFilms()),R.layout.pendings_item_list_content, getContext());

        View recyclerView = v.findViewById(R.id.fragment_pendings);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        return v;
    }

    // Se asigna a la lista los datos de las películas pendientes
    public void setupRecyclerView(@NonNull RecyclerView recyclerView){
        recyclerView.setAdapter(filmListAdapter);
    }

    @Override
    public void onResume() {
        Log.i("PRUEBA", "En onResume()");
        getActivity().runOnUiThread(() -> filmListAdapter.swap(new ArrayList<>(pendingsFragmentViewModel.getUserPendingFilms())));
        super.onResume();
    }
}