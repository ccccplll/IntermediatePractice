package com.example.demo;

import com.example.demo.Service.ScheduledTaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@MapperScan("com.example.demo.mapper")

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	public static void main(String[] args) {
		System.setProperty("debug", "true");
		SpringApplication.run(DemoApplication.class, args);
		Chat chat = new Chat();
		boolean result = chat.getAccessToken();
		System.out.println(result);
	}

}
