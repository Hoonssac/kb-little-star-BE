package com.kbstar.littlestar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kbstar.littlestar.pokemon.mapper")
public class LittlestarApplication {
	public static void main(String[] args) {
		SpringApplication.run(LittlestarApplication.class, args);
	}
}
