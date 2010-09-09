package com.tommumania.freemail;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailTypeToTemplateFilenameMapperTest {

    @Test
    public void shouldMapHelloWorldTypeToCorrectFilename() {
        assertThat(new EmailTypeToTemplateFilenameMapper().map(EmailType.HELLO_WORLD, EmailFormat.PLAIN_TEXT), is("hello_world-plain.ftl"));
    }

}
