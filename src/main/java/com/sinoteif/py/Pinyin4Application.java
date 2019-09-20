package com.sinoteif.py;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sinoteif.py.mapper")
public class Pinyin4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pinyin4Application.class, args);
	}

}
