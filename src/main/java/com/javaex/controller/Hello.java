package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= "/hello")
public class Hello {

	@RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST})
	public String hello() {
		System.out.println("/hellospring/hello");
		return "hello";
	}
}
