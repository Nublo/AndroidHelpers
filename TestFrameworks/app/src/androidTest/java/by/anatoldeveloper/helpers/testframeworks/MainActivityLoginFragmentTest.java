package by.anatoldeveloper.helpers.testframeworks;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import by.anatoldeveloper.helpers.testframeworks.activity.LoginActivity;
import by.anatoldeveloper.helpers.testframeworks.ui.fragments.LoginFragment;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Nublo on 28.08.2016.
 * Copyright Nublo
 */
public class MainActivityLoginFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkErrorShowingForIncorrectUser() {
        onView(withId(R.id.login_invalid_credentials)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.login_username)).perform(typeText("test"));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText("test"));
        closeSoftKeyboard();
        onView(withId(R.id.login_enter)).perform(click());
        onView(withId(R.id.login_invalid_credentials)).check(matches(isDisplayed()));
    }

    @Test
    public void successLogin() {
        onView(withId(R.id.login_invalid_credentials)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.login_username)).perform(typeText(LoginFragment.DEFAULT_LOGIN));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText(LoginFragment.DEFAULT_PASSWORD));
        closeSoftKeyboard();
        onView(withId(R.id.login_enter)).perform(click());

        IdlingResource idlingResource = startTiming(1500);
        onView(withId(R.id.login_invalid_credentials)).check(matches(isDisplayed())).
                check(matches(withText(InstrumentationRegistry.getTargetContext().getString(R.string.success_login))));
        stopTiming(idlingResource);
    }

    public IdlingResource startTiming(long time) {
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(time);
        Espresso.registerIdlingResources(idlingResource);
        return idlingResource;
    }

    public void stopTiming(IdlingResource idlingResource) {
        Espresso.unregisterIdlingResources(idlingResource);
    }
}