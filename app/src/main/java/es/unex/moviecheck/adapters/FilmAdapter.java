package es.unex.moviecheck.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import es.unex.moviecheck.R;
import es.unex.moviecheck.model.Films;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmsAdapterViewHolder> {
    private List<Films> filmList;
    private final FilmListener filmListener;
    /*
     * Campo para pasarle al Adapter el layout sobre el que actuar. Esto implica que los IDs de las Views de cada layout deben ser iguales (ej: ivMoviePoster)
     * Los layouts 'afectados' son explore_item_grid_content.xml, favorites_item_list_content.xml y pendings_item_list_content.xml
     * Se trata de un Int ya que el ID del layout se resuelve como un entero.
     */
    private final int listItemLayout;

    public FilmAdapter(List<Films> list, int listItemLayout, Context context) {
        this.filmList = list;
        this.listItemLayout = listItemLayout;
        filmListener = (FilmListener) context;
    }

    @NonNull
    @Override
    public FilmsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.listItemLayout, parent,false);
        return new FilmsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(filmList.get(position).getTitle());
        Glide.with(holder.image.getContext()).load("https://image.tmdb.org/t/p/original/"+filmList.get(position).getPosterPath()).into(holder.image);
        holder.date.setText(filmList.get(position).getReleaseDate().split("-")[0]);
        holder.itemView.setOnClickListener(view -> filmListener.onFilmSelected(filmList.get(position)));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmsAdapterViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final ImageView image;
        final TextView date;

        FilmsAdapterViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvMovieTitle);
            image = view.findViewById(R.id.ivMoviePoster);
            date = view.findViewById(R.id.tvMovieDate);
        }
    }

    public void swap(List<Films> filmList){
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    public void clear() {
        filmList.clear();
        notifyDataSetChanged();
    }

    public interface FilmListener{
        void onFilmSelected(Films film);
    }
}
