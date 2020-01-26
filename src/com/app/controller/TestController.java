package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController 
{
	public TestController() {
		System.out.println("inside Test Controller");
	}
	@GetMapping
	public String test()
	{
		return "Sunbeam";
	}
}
