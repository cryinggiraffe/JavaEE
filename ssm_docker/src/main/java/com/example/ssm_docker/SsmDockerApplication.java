package com.example.ssm_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SsmDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmDockerApplication.class, args);
	}

}
