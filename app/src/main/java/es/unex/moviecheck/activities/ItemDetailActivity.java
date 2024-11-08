package es.unex.moviecheck.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import es.unex.moviecheck.AppContainer;
import es.unex.moviecheck.MyApplication;
import es.unex.moviecheck.adapters.CommentAdapter;
import es.unex.moviecheck.adapters.TabsViewPagerAdapter;
import es.unex.moviecheck.model.Comments;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.shared_interfaces.ItemDetailInterface;
import es.unex.moviecheck.viewmodels.ItemDetailActivityViewModel;
import es.unex.moviecheck.R;

public class ItemDetailActivity extends AppCompatActivity implements ItemDetailInterface, CommentAdapter.DeleteCommentInterface {

    // Objetos necesarios para la gestión de los Tabs
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    // Objeto película con el que se recupera la información básica de la película seleccionada
    private Films film;

    // Referencia al ViewModel
    ItemDetailActivityViewModel itemDetailActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        setTitle(R.string.detail_title);

        tabLayout = findViewById(R.id.tlDetail);
        viewPager2 = findViewById(R.id.vpDetail);

        // Para el ViewModel
        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        itemDetailActivityViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factory).get(ItemDetailActivityViewModel.class);

        // Se obtiene la película de la que se quiere mostrar información
        film = (Films) getIntent().getSerializableExtra("FILM");

        itemDetailActivityViewModel.getFilm(film);

        viewPager2.setAdapter(new TabsViewPagerAdapter(this.getSupportFragmentManager(), getLifecycle()));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do not needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do not needed
            }
        });
    }

    /**
     * Devuelve la película seleccionada. Se emplea para la comunicación con los fragmentos que implementan las tabs.
     * @return Película que ha sido seleccionada y de la que se quiere mostrar la información de detalle
     */
    @Override
    public Films getFilmSelected() {
        return film;
    }

    @Override
    public void deleteComment(Comments comment) {
        itemDetailActivityViewModel.deleteComment(comment);
    }
}