package com.mihey.sqsaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;

@SpringBootApplication(exclude = {ContextInstanceDataAutoConfiguration.class})
public class SqsawsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsawsApplication.class, args);
	}

}
