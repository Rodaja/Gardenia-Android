package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryTest {
    private Country country;

    @Before
    public void setUp() {
        country = new Country();
    }

    @Test
    public void userNotNull() throws Exception {
        assertNotNull(country);
    }

    @Test
    public void nombre_abreviado() {
        assertEquals("C.Norte", country.nombre_abreviado("C Norte"));

    }

    @Test
    public void nombre_abreviadoFalloSiglas() {
        assertNotEquals("USA", country.nombre_abreviado("Estados Unidos"));

    }
}