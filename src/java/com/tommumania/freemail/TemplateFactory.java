package com.tommumania.freemail;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;

public class TemplateFactory {
    private Configuration configuration;

    public TemplateFactory() {
        configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File("src/resources"));
        } catch (IOException e) {
            throw new RuntimeException("Could not set template directory: " + e.getMessage());
        }
    }

    public Template getTemplate(String filename) {
        try {
            return configuration.getTemplate(filename);
        } catch (IOException e) {
            throw new RuntimeException("Could not get template: " + filename + " " + e.getMessage());
        }
    }
}
