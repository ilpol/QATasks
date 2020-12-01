package ru.stqa.maven.tests;

import org.junit.Before;
import ru.stqa.maven.app.Application;

public class TestBase {
    public Application app;

    @Before
    public void start(){
        app = new Application();
    }
}
