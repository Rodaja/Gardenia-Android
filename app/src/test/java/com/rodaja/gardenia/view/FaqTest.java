package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FaqTest {
    private Faq faq;

    @Before
    public void setUp() {
        faq = new Faq();
    }

    @Test
    public void faqNotNull() throws Exception {
        assertNotNull(faq);
    }
}