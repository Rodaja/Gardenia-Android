package com.rodaja.gardenia.view;

import android.content.Intent;

import com.rodaja.gardenia.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {

    @Test
    public void editTextsTest(){
        ActivityTestRule<Signup> activityRule = new ActivityTestRule<>(Signup.class, true,true);
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
        // Type text and then press the button.
        onView(withId(R.id.etEmail)).perform(typeText("gardenia@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.etPasswordRepeat)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btnSignUp)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.etEmail)).check(matches(withText("gardenia@gmail.com")));
        onView(withId(R.id.etPassword)).check(matches(withText("1234")));
        onView(withId(R.id.etPasswordRepeat)).check(matches(withText("1234")));
    }
}
