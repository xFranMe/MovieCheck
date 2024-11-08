package es.unex.moviecheck.fragments;

import static android.view.View.INVISIBLE;
import static java.lang.Math.round;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.unex.moviecheck.AppContainer;
import es.unex.moviecheck.MyApplication;
import es.unex.moviecheck.R;
import es.unex.moviecheck.adapters.CommentAdapter;
import es.unex.moviecheck.model.Comments;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.shared_interfaces.ItemDetailInterface;
import es.unex.moviecheck.viewmodels.ItemDetailSocialFragmentViewModel;

public class ItemDetailSocialFragment extends Fragment  {

    // Referencias a vistas de la UI
    private ImageView ivMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieDate;
    private TextView tvRatingSocial;
    private EditText etCommentSocial;
    private ImageButton ibSendComment;
    private Button bAddRating;
    private NumberPicker npAddRating;

    private ItemDetailInterface itemDetailInterface;

    private CommentAdapter commentAdapter;

    private Films film;

    // Referencia al ViewModel
    ItemDetailSocialFragmentViewModel itemDetailSocialFragmentViewModel;

    private SharedPreferences loginPreferences;

    public ItemDetailSocialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loginPreferences = getActivity().getSharedPreferences(getActivity().getPackageName() + "_preferences", Context.MODE_PRIVATE);

        // Para el ViewModel
        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        itemDetailSocialFragmentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factory).get(ItemDetailSocialFragmentViewModel.class);

        View v = inflater.inflate(R.layout.fragment_item_detail_social, container, false);
        View recyclerView = v.findViewById(R.id.social_comments);
        assert recyclerView != null;
        commentAdapter = new CommentAdapter(new ArrayList<>(), loginPreferences.getString("USERNAME", ""), getContext());
        setupRecyclerView((RecyclerView) recyclerView);

        film = itemDetailInterface.getFilmSelected();
        itemDetailSocialFragmentViewModel.changeFilm(film);

        // Se obtienen los datos y los comentarios más recientes de la película
        itemDetailSocialFragmentViewModel.getComments().observe(getActivity(), comments -> commentAdapter.swap(comments));

        getViewsReferences(v);
        npAddRating.setMinValue(1);
        npAddRating.setMaxValue(10);

        updateUI();

        bAddRating.setOnClickListener(view -> {
            if(itemDetailSocialFragmentViewModel.filmNotRated(film)){
                addRating(npAddRating.getValue());
            }
        });

        ibSendComment.setOnClickListener(view -> {
            String commentBody = etCommentSocial.getText().toString();
            if (!commentBody.equals("")) {
                addComment(commentBody);
                etCommentSocial.setText("");
            } else {
                Snackbar.make(view, R.string.empty_comment, 2500)
                        .show();
            }
        });
        return v;
    }

    private void getViewsReferences(View view) {
        ivMoviePoster = view.findViewById(R.id.ivMoviePoster);
        tvMovieTitle = view.findViewById(R.id.tvMovieTitle);
        tvMovieDate = view.findViewById(R.id.tvMovieDate);
        tvRatingSocial = view.findViewById(R.id.tvRatingSocial);
        etCommentSocial = view.findViewById(R.id.etCommentSocial);
        ibSendComment = view.findViewById(R.id.ibSendComment);
        bAddRating = view.findViewById(R.id.bAddRating);
        npAddRating = view.findViewById(R.id.npAddRating);
    }

    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(commentAdapter);
    }

    private void updateUI() {
        getActivity().runOnUiThread(() -> {
            tvMovieTitle.setText(film.getTitle());
            tvMovieDate.setText(film.getReleaseDate().split("-")[0]);
            updateRating();
            Glide.with(getContext()).load("https://image.tmdb.org/t/p/original/" + film.getPosterPath()).into(ivMoviePoster);
        });
    }

    private void addComment(String commentBody) {
        Comments comment = new Comments(loginPreferences.getString("USERNAME", ""), film.getId(), commentBody);
        itemDetailSocialFragmentViewModel.addComment(comment);
    }

    @SuppressLint("SetTextI18n")
    private void addRating(int rating){

        itemDetailSocialFragmentViewModel.addRating(film, rating);

        film.setTotalVotesMovieCheck(film.getTotalVotesMovieCheck()+1);
        film.setTotalRatingMovieCheck(film.getTotalRatingMovieCheck()+rating);

        getActivity().runOnUiThread(() -> {
            tvRatingSocial.setText(Double.toString((double) film.getTotalRatingMovieCheck()/film.getTotalVotesMovieCheck()));
            npAddRating.setEnabled(false);
            npAddRating.setVisibility(INVISIBLE);
            bAddRating.setEnabled(false);
            bAddRating.setVisibility(INVISIBLE);
            tvRatingSocial.setText(Double.toString(round((double) film.getTotalRatingMovieCheck()/film.getTotalVotesMovieCheck())));
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateRating(){
        if(film.getTotalVotesMovieCheck()!=0){
            tvRatingSocial.setText(Double.toString(round((double) film.getTotalRatingMovieCheck()/film.getTotalVotesMovieCheck())));
        } else {
            tvRatingSocial.setText("----");
        }
        if (!itemDetailSocialFragmentViewModel.filmNotRated(film)){
            npAddRating.setEnabled(false);
            npAddRating.setVisibility(INVISIBLE);
            bAddRating.setEnabled(false);
            bAddRating.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            itemDetailInterface = (ItemDetailInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement ItemDetailInterface");
        }
    }

    @Override
    public void onResume() {
        updateUI();
        super.onResume();
    }
}