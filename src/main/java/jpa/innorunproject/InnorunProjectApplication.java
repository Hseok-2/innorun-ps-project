package jpa.innorunproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InnorunProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnorunProjectApplication.class, args);
    }

}
