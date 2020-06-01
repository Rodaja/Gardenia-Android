package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailsTest {
    private Details details;

    @Before
    public void setUp() {
        details = new Details();
    }

    @Test
    public void detailsNotNull() throws Exception {
        assertNotNull(details);
    }

}