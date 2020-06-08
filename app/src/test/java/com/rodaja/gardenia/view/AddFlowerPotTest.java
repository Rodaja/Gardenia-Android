package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddFlowerPotTest {

    private AddFlowerPot addFlowerPot;

    @Before
    public void setUp() {
        addFlowerPot = new AddFlowerPot();
    }

    @Test
    public void addFlowerPotNotNull() throws Exception {
        assertNotNull(addFlowerPot);
    }
}