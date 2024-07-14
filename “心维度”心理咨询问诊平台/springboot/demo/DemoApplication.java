package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo.mapper")

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.setProperty("debug", "true");
		SpringApplication.run(DemoApplication.class, args);
		Chat chat = new Chat();
		boolean result = chat.getAccessToken();
		System.out.println(result);
	}

}
