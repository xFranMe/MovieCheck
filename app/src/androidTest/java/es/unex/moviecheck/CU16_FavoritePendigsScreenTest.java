package es.unex.moviecheck;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import es.unex.moviecheck.activities.LoginActivity;
import es.unex.moviecheck.model.Films;
import es.unex.moviecheck.room.FilmsDatabase;
import es.unex.moviecheck.support.AppExecutors;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CU16_FavoritePendigsScreenTest {

    public Films films;
    public Context c = ApplicationProvider.getApplicationContext();

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);



    @Before
    public void initTest(){
        List<Integer> genresids = new ArrayList<>();
        genresids.add(28);
        genresids.add(14);
        films = new Films(true, "", "es", 0.0,"Tequeños para ti",false,0,0,
                "Tequeños para ti",genresids, "","Así se hacen los buenos tequeños, de nada","2022-12-07",0.0,0,0);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(c);
                db.filmDAO().insertFilm(films);
                for(Integer genre: genresids){
                    db.filmsGenresListDAO().insertFilmGenre(films.getId(), genre);
                }
            }
        });
    }

    @After
    public void finishTest(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(c);
                db.filmDAO().deleteFilm(films);
                db.filmsGenresListDAO().deleteFilmGenre(films.getId());
            }
        });
    }

    @Test
    public void c16_FavoritePendigsScreenTest() {
        String title = films.getTitle();

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.tvRegisterLogin), withText(R.string.register),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etUsernameRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText.perform(scrollTo(), replaceText("Usuario"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etEmailRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatEditText2.perform(scrollTo(), replaceText("usuario@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.etPasswordRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatEditText3.perform(scrollTo(), replaceText("Usuario1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.etRepeatPasswordRegister),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                10)));
        appCompatEditText4.perform(scrollTo(), replaceText("Usuario1"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.bRegister), withText(R.string.register),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.navigation_favorites), withContentDescription(R.string.title_favorites),
                        withParent(withParent(withId(R.id.nav_view))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.navigation_pendings), withContentDescription(R.string.title_pendings),
                        withParent(withParent(withId(R.id.nav_view))),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tvMovieTitle), withText(title),
                        withParent(withParent(withId(R.id.fragment_explore))),
                        isDisplayed()));
        textView.check(matches(withText(title)));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.fragment_explore),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView2 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView2.check(matches(withText(R.string.detail_title)));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tvMovieTitleDetail),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText(title)));

        ViewInteraction button = onView(
                allOf(withId(R.id.bToggleFavoriteDetail), withText(R.string.detail_add_favorites),
                        withParent(allOf(withId(R.id.linearLayout2),
                                withParent(IsInstanceOf.<View>instanceOf(ViewGroup.class)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.bTogglePendingDetail), withText(R.string.detail_add_pendant),
                        withParent(allOf(withId(R.id.linearLayout2),
                                withParent(IsInstanceOf.<View>instanceOf(ViewGroup.class)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.bToggleFavoriteDetail), withText(R.string.detail_add_favorites),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                13)),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.bTogglePendingDetail), withText(R.string.detail_add_pendant),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                13)),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        pressBack();

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_favorites), withContentDescription(R.string.title_favorites),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView5.check(matches(withText(R.string.title_favorites)));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tvMovieTitle),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText(title)));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.ibAction), withContentDescription(R.string.movie_fav_button),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.ibAction), withContentDescription(R.string.movie_fav_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView6.check(matches(withText(R.string.title_favorites)));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_pendings), withContentDescription(R.string.title_pendings),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView7.check(matches(withText(R.string.title_pendings)));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.tvMovieTitle),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView8.check(matches(withText(title)));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.ibAction), withContentDescription(R.string.movie_pending_button),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.ibAction), withContentDescription(R.string.movie_pending_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction textView9 = onView(
                allOf(withParent(allOf(withId(androidx.preference.R.id.action_bar),
                                withParent(withId(androidx.preference.R.id.action_bar_container)))),
                        isDisplayed()));
        textView9.check(matches(withText(R.string.title_pendings)));

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_profile), withContentDescription(R.string.title_profile),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.bDeleteAccount), withText(R.string.profile_delete_account),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.bDelete), withText(R.string.profile_delete_permanently),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton5.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
