package com.jaesay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class TinyProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(TinyProject1Application.class, args);
	}
}
