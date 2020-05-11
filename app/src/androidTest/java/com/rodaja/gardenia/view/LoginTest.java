package com.rodaja.gardenia.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginTest  {

    @Test
    public void editTextTest(){
        ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class, true,true);
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
        // Type text and then press the button.
        onView(withId(R.id.etEmail)).perform(typeText("gardenia@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.etEmail)).check(matches(withText("gardenia@gmail.com")));
        onView(withId(R.id.etPassword)).check(matches(withText("1234")));

        /*
        Intents.init();
        intended(hasComponent(Home.class.getName()));
         */
    }
}
