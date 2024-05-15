package com.example.demo.User;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class userConfig {

    @Bean
    CommandLineRunner commandLineRunner(UtenteRepository utenteRepository){
        return args -> {
            UserOfPopster Marco = new UserOfPopster("Marc0", "Marco1A3B", "Marco", "Meneghetti", LocalDate.of(1996, 1, 12), "marcom962015@gmail.com", "user");
            UserOfPopster Simone = new UserOfPopster("Dan1", "Dan186", "Daniele", "Parapiglia", LocalDate.of(1997, 6, 24), "dani1@gmaile.com","user");
            UserOfPopster Gianni = new UserOfPopster("Giann1", "xFerRecordsPass", "Gianni", "Roberti", LocalDate.of(1875,10, 23), "gianni.roberti@FXRECORDS.com","Vendor");
            utenteRepository.saveAll(List.of(Marco, Simone, Gianni));
        };
    }
}
