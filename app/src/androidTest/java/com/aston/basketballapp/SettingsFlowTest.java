package com.aston.basketballapp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsFlowTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void settingsFlowTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.main_app_bar_layout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.settings),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                6),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction materialCardView = onView(
                allOf(withId(R.id.settings_change_favourite_team),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container_view),
                                        0),
                                1),
                        isDisplayed()));
        materialCardView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.favourite_team_recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.favourite_team_recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.main_app_bar_layout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction materialCardView2 = onView(
                allOf(withId(R.id.settings_customisation_settings),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container_view),
                                        0),
                                0),
                        isDisplayed()));
        materialCardView2.perform(click());

        ViewInteraction switchMaterial = onView(
                allOf(withId(R.id.points_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial.perform(click());

        ViewInteraction switchMaterial2 = onView(
                allOf(withId(R.id.points_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial2.perform(click());

        ViewInteraction switchMaterial3 = onView(
                allOf(withId(R.id.points_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial3.perform(click());

        ViewInteraction switchMaterial4 = onView(
                allOf(withId(R.id.points_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial4.perform(click());

        ViewInteraction switchMaterial5 = onView(
                allOf(withId(R.id.points_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial5.perform(click());

        ViewInteraction switchMaterial6 = onView(
                allOf(withId(R.id.blocks_percentage_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial6.perform(click());

        ViewInteraction switchMaterial7 = onView(
                allOf(withId(R.id.steals_percentage_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial7.perform(click());

        ViewInteraction switchMaterial8 = onView(
                allOf(withId(R.id.free_throw_percentage_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial8.perform(click());

        ViewInteraction switchMaterial9 = onView(
                allOf(withId(R.id.overall_field_goal_percentage_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial9.perform(click());

        ViewInteraction switchMaterial10 = onView(
                allOf(withId(R.id.free_throws_made_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial10.perform(click());

        ViewInteraction switchMaterial11 = onView(
                allOf(withId(R.id.rebounds_per_game_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial11.perform(click());

        ViewInteraction switchMaterial12 = onView(
                allOf(withId(R.id.plus_minus_slider),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1),
                        isDisplayed()));
        switchMaterial12.perform(click());
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
