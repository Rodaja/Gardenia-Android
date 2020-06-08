package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeTest {
    private Home home;

    @Before
    public void setUp() {
        home = new Home();
    }

    @Test
    public void homeNotNull() throws Exception {
        assertNotNull(home);
    }
}