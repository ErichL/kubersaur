package com.darthShana.kubersaur.generator.microservice.service;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ApplicationLauncherGenerator implements Generator {
    private final String microserviceName;
    private final String baseDir;
    private final String templateDirectory;
    private final Org org;
    private final String filePath;

    public ApplicationLauncherGenerator(String microserviceName, String baseDir, String templateDirectory, Org org) {
        this.microserviceName = microserviceName;
        this.baseDir = baseDir;
        this.templateDirectory = templateDirectory;
        this.org = org;
        this.filePath = baseDir+"/src/test/groovy/"+org.getPackagePathDirs();
    }

    @Override
    public String organisationPackage() {
        return org.getPackagePath();
    }

    @Override
    public String organisationName() {
        return org.getName();
    }

    public String microserviceName(){
        return microserviceName;
    }

    public void generate() throws IOException {
        new File(filePath).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"BatheRunner.mustache");
        mustache.execute(new FileWriter(filePath+"/"+"BatheRunner.groovy"), this).flush();
    }
}