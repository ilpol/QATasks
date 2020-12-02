package ru.stqa.cucumber.tests;

import org.junit.Before;
import ru.stqa.cucumber.app.Application;

public class TestBase {
    public Application app;

    @Before
    public void start(){
        app = new Application();
    }
}
