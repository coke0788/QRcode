package com.gd.qrtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//db 사용 안할거라서 자동 설정으로 설정.
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class QrtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrtestApplication.class, args);
	}

}
