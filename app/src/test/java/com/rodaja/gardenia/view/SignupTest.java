package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignupTest {
    private Signup signup;

    @Before
    public void setUp() {
        signup = new Signup();
    }

    @Test
    public void signupNotNull() throws Exception {
        assertNotNull(signup);
    }
}