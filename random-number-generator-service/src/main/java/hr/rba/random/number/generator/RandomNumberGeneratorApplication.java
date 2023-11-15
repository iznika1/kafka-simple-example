package hr.rba.random.number.generator;

import hr.rba.random.number.generator.config.RandomNumberGeneratorProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {RandomNumberGeneratorProperties.class})
public class RandomNumberGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomNumberGeneratorApplication.class, args);
	}



}
