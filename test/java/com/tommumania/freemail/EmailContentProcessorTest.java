package com.tommumania.freemail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailContentProcessorTest {
    private EmailContentProcessor processor;

    @Mock
    private TemplateFactory templatefactory;
    @Mock
    private EmailTypeToTemplateFilenameMapper emailTypeToTemplateFilenameMapper;

    @Before
    public void setUp() {
        processor = new EmailContentProcessor(templatefactory, emailTypeToTemplateFilenameMapper);

    }

    @Test
    public void shouldProcessTemplatesIntoEmailContent() throws IOException, TemplateException {
        Map<String, Object> map = mock(Map.class);
        Template template = mock(Template.class);

        when(emailTypeToTemplateFilenameMapper.map(EmailType.HELLO_WORLD)).thenReturn("some filename");
        when(templatefactory.getTemplate("some filename")).thenReturn(template);

        processor.process(map, EmailType.HELLO_WORLD);
        
        verify(template).process(eq(map), any(StringWriter.class));
    }


}
