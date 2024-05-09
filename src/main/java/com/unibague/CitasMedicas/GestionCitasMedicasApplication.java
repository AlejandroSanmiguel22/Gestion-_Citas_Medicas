package com.unibague.CitasMedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.unibague.CitasMedicas.interfaz")
public class GestionCitasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCitasMedicasApplication.class, args);
	}

}
