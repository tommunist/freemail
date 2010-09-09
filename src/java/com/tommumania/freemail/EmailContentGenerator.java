package com.tommumania.freemail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

public class EmailContentGenerator {
    private TemplateFactory templatefactory;
    private TemplateFilenameFactory templateFilenameFactory;

    public EmailContentGenerator(TemplateFactory templatefactory, TemplateFilenameFactory templateFilenameFactory) {
        this.templatefactory = templatefactory;
        this.templateFilenameFactory = templateFilenameFactory;
    }

    public String process(Map<String, Object> map, MessageType emailType, EmailFormat emailFormat) throws IOException, TemplateException {
        String templateFilename = templateFilenameFactory.getFilename(emailType, emailFormat);
        Template template = templatefactory.getTemplate(templateFilename);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

    }
}
