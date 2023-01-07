package es.unex.moviecheck.sharedInterfaces;

import es.unex.moviecheck.model.Films;

// Interfaz para comunicar este Fragment con su actividad (ItemDetailActivity)
public interface ItemDetailInterface{
    Films getFilmSelected();

}
