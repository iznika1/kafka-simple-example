package hr.rba.letter.presenter.service;

import hr.rba.letter.presenter.service.feed.data.kafka.number.config.KafkaFeedNumberProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@EnableConfigurationProperties(value = {KafkaFeedNumberProperties.class})
public class KafkaLetterPresenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaLetterPresenterApplication.class, args);
	}

}
