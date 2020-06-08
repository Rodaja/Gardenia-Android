package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {
    private Profile profile;

    @Before
    public void setUp() {
        profile = new Profile();
    }

    @Test
    public void profileNotNull() throws Exception {
        assertNotNull(profile);
    }
}