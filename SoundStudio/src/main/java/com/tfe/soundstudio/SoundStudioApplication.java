package com.tfe.soundstudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.tfe.soundstudio")
public class SoundStudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoundStudioApplication.class, args);
	}
}
