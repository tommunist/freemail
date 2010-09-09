package com.tommumania.freemail;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TemplateFilenameFactoryTest {

    @Test
    public void shouldMapHelloWorldTypeToCorrectFilename() {
        assertThat(new TemplateFilenameFactory().getFilename(MessageType.HELLO_WORLD, EmailFormat.PLAIN_TEXT), is("hello_world-plain.ftl"));
    }

}
