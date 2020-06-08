package com.rodaja.gardenia.view;

import android.content.Intent;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeTest {

    @Test
    public void recyclerViewTest(){
        ActivityTestRule<Home> activityRule = new ActivityTestRule<>(Home.class, true,true);
        User user = new User();

        ArrayList<FlowerPot> macetas = new ArrayList<FlowerPot>();
        macetas.add(new FlowerPot("11:11:11:11:11:11", "1", "F1","image", 30, 30, 30, true));
        macetas.add(new FlowerPot("22:22:22:22:22:22","1", "F2","image", 30, 30, 30, false));
        macetas.add(new FlowerPot("33:33:33:33:33:33", "1", "F3","image", 30, 30, 30, false));
        macetas.add(new FlowerPot("44:44:44:44:44:44", "1", "F4", "image", 30, 30, 30, false));
        user.setListFlowerPots(macetas);

        Intent intent = new Intent();
        intent.putExtra("user", user);
        activityRule.launchActivity(intent);

        onView(withId(R.id.rvHome)).perform(swipeUp());

        onView(withId(R.id.rvHome)).check(matches(isDisplayed()));
    }

    @Test
    public void addFlowerpotButtonTest(){
    }

    @Test
    public void profileButtonTest(){

    }
}
