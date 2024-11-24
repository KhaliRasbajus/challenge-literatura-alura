package com.alura.literatura.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.literatura.challenge.main.Menu;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    
        Menu menu = new Menu();
        menu.play();
    }
    
    

}
