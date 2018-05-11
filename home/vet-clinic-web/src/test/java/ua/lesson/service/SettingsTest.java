package ua.lesson.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {
    @Test
    public void value() throws Exception {
        Settings set=Settings.getInstance();
        assertEquals("jdbc:postgresql://127.0.0.1:5432/ClinicDatabase",set.value("jdbc.url"));
        assertEquals("postgres",set.value("jdbc.username"));
        assertEquals("oneres1",set.value("jdbc.password"));
    }

}