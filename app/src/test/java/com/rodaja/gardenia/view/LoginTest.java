package com.rodaja.gardenia.view;

import com.rodaja.gardenia.model.entity.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {
    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    @Test
    public void userNotNull() throws Exception {
        assertNotNull(login);
    }

    @Test
    public void loginRequest() throws Exception {

    }
}