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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Test
    public void settingsTest(){
        ArrayList<FlowerPot> macetas = new ArrayList<FlowerPot>();
        macetas.add(new FlowerPot("11:11:11:11:11:11","1", "F1","image", 30, 30, 30, true));
        macetas.add(new FlowerPot("22:22:22:22:22:22","1", "F2","image", 30, 30, 30, false));
        macetas.add(new FlowerPot("33:33:33:33:33:33","1", "F3","image", 30, 30, 30, false));
        macetas.add(new FlowerPot("44:44:44:44:44:44","1", "F4","image", 30, 30, 30, false));
        User user = new User("gardenia@gmail.com", "Gardi", "Gar", "Denia", "1234", "Spain", "1234", "celsius", "light", false, macetas);

        ActivityTestRule<Settings> activityRule = new ActivityTestRule<>(Settings.class, true,true);
        Intent intent = new Intent();
        intent.putExtra("user", user);
        activityRule.launchActivity(intent);

        // Type text and then press the button.
        onView(withId(R.id.constLUnidadTemperaturaEditable)).perform(click());
        onView(withId(R.id.constLNotificacionesEditable)).perform(click());
        onView(withId(R.id.constLTemaEditable)).perform(click());
    }
}
