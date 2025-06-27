package com.hatim.banking.config;

import com.hatim.banking.Test;
import org.springframework.stereotype.Component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Test test = new Test();
        test.output();

    }
}

