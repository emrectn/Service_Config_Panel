package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.spring.model")
@ComponentScan({"com.example.demo", "controller"})
@SpringBootApplication
public class MainApplication 
{
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		System.out.println("Program Calisti");
		
		Generic <Integer> obj1 = new Generic<Integer>(1);
        Generic <String> obj2 = new Generic<String>("Emre");
        Generic <Long> obj3 = new Generic<Long>(3L);
        Generic <Boolean> obj4 = new Generic<Boolean>(true);
        
        obj1.show();
        obj2.show();
        obj3.show();
        obj4.show();
        System.out.println(multiply(3, 2));
        System.out.println(multiply(1L, 2));
        System.out.println(multiply(3, 2D));
	}
	
	public static <T extends Number> double multiply(T value, T factor) {
		return value.doubleValue()*factor.doubleValue();
	}
}
