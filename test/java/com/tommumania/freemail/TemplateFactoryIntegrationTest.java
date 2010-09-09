package com.tommumania.freemail;

import org.junit.Before;
import org.junit.Test;

public class TemplateFactoryIntegrationTest {
    private TemplateFactory templateFactory;

    @Before
    public void setUp() {
        templateFactory = new TemplateFactory();
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException() {
        templateFactory.getTemplate("some rubbish filename");
    }

    @Test
    public void shouldGetTemplateForHelloWorld() {
        templateFactory.getTemplate("hello_world-plain.ftl");
    }
}
