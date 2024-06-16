package com.ag.pst.codeexercise.carsearch.configuration;

import com.ag.pst.codeexercise.carsearch.model.Car;
import com.ag.pst.codeexercise.carsearch.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * A method to initialize and populate the CarRepository with sample data.
     *
     * @param repository The CarRepository instance to be populated.
     * @return A CommandLineRunner to execute the data population logic.
     */
    @Bean
    public CommandLineRunner demo(CarRepository repository) {
        return (args) -> {
            repository.save(new Car(null, 4.5, 1500, 220, "Red"));
            repository.save(new Car(null, 4.0, 1300, 200, "Blue"));
            repository.save(new Car(null, 5.0, 1800, 250, "Black"));
        };
    }
}
