package com.challenge.goldenraspberry.awards.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    private final CsvLoader csvLoader;

    public StartupRunner(CsvLoader csvLoader) {
        this.csvLoader = csvLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        csvLoader.loadCsv();
    }
}
