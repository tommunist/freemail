package com.tommumania.freemail;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class EmailContentProcessor {
    private TemplateFactory templatefactory;
    private EmailTypeToTemplateFilenameMapper emailTypeToTemplateFilenameMapper;

    public EmailContentProcessor(TemplateFactory templatefactory, EmailTypeToTemplateFilenameMapper emailTypeToTemplateFilenameMapper) {
        this.templatefactory = templatefactory;
        this.emailTypeToTemplateFilenameMapper = emailTypeToTemplateFilenameMapper;
    }

    public String process(Map<String, Object> map, EmailType emailType) throws IOException, TemplateException {
        String templateFilename = emailTypeToTemplateFilenameMapper.map(emailType);
        Template template = templatefactory.getTemplate(templateFilename);
        StringWriter writer = new StringWriter();

        template.process(map, writer);

        return writer.toString();

    }
}
