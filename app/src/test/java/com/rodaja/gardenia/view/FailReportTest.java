package com.rodaja.gardenia.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FailReportTest {
    private FailReport failReport;

    @Before
    public void setUp() {
        failReport = new FailReport();
    }

    @Test
    public void failReportNotNull() throws Exception {
        assertNotNull(failReport);
    }
}