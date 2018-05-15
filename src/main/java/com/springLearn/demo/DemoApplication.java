package com.springLearn.demo;

import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String HelloWorld(){
		return "hello SpringSecurity";
	}

	@RequestMapping("/hello")
	public String HelloSecurity(){
		return "hello Security";
	}

	@RequestMapping("/Auth")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('Role_USER')")
	public String userAuth(){
		return "hello userAuth";
	}

	@RequestMapping("/test")
	@PreAuthorize("#id<10 or principal.username.equals(#username) and #user.username.equals('abc')")
	public Integer test(Integer id, String username , User user){
		return id;
	}

	@RequestMapping("/test1")
	@PostAuthorize("returnObject%2 == 0")
	public Integer test1(Integer id){
		return id;
	}

	@RequestMapping("/test2")
	@PreFilter("filterObject%2 == 0")
	public List<Integer> test1(List<Integer> id){
		return id;
	}
}
