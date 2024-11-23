package com.project.lms.ExpenseTrackerLms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExpenseTrackerLmsApplication {

	public static void main(String[] args) {
	SpringApplication.run(ExpenseTrackerLmsApplication.class, args);

	}

}
