package com.highgroup.highgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HighgroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighgroupApplication.class, args);
	}

}
