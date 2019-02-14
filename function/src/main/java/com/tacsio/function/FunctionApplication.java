package com.tacsio.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class FunctionApplication {

	@Bean
	public Function<Foo, Foo> uppercase() {
		return foo -> new Foo(foo.getValue().toUpperCase());
	}

	public static void main(String[] args) {
		SpringApplication.run(FunctionApplication.class, args);
	}

}

class Foo {

	private String value;

	public Foo() {
	}

	public Foo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

