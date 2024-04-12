package com.unibague.CitasMedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GestionCitasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCitasMedicasApplication.class, args);
	}

}
