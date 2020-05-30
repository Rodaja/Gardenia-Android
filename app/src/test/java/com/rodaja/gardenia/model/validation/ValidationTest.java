package com.rodaja.gardenia.model.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {


    @Test
    public void emailNotNull() {
        assertNotNull(Validation.validarEmail("roberechev@gmail.com"));
    }

    @Test
    public void validarEmailCorrecto() {
        assertEquals(true, Validation.validarEmail("roberechev@gmail.com"));
    }

    @Test
    public void validarEmailFalloArroba() {
        assertEquals(false, Validation.validarEmail("roberechevgmail.com"));
    }

    @Test
    public void validarEmailFalloPunto() {
        assertEquals(false, Validation.validarEmail("roberechev@gmailcom"));
    }


    @Test
    public void validarPassword() {
        assertEquals(true, Validation.validarPassword("pruebaContrasena"));
    }

    @Test
    public void validarPasswordFalloNada() {
        assertEquals(false, Validation.validarPassword(""));
    }

    @Test
    public void validarPasswordFalloNulo() {
        assertEquals(false, Validation.validarPassword(null));
    }


}