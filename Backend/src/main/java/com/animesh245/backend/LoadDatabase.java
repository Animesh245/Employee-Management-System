package com.animesh245.backend;

import com.animesh245.backend.model.Employee;
import com.animesh245.backend.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase
{
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository)
    {
        return args -> {
            log.info("Preloading " + repository.save(new Employee(1,"Sayed","Fahim","foong@yahoo.com")));
            log.info("Preloading " + repository.save(new Employee(2,"Shakil","Khan","shakil@yahoo.com")));
        };
    }
}
