package com.xiaoxiong.nbst01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.xiaoxiong.nbst01.mapper")
@SpringBootApplication
@ServletComponentScan
public class St01Application {

	public static void main(String[] args) {
		SpringApplication.run(St01Application.class, args);
	}
}
