package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {
    private Settings settings;

    @Before
    public void setUp() {
        settings = new Settings();
    }

    @Test
    public void settingsNotNull() throws Exception {
        assertNotNull(settings);
    }
}