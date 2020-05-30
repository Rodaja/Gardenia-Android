package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddFlowerPotWebViewTest {

    private AddFlowerPotWebView addFlowerPotWebView;

    @Before
    public void setUp() {
        addFlowerPotWebView = new AddFlowerPotWebView();
    }

    @Test
    public void addFlowerPotWebViewNotNull() throws Exception {
        assertNotNull(addFlowerPotWebView);
    }
}