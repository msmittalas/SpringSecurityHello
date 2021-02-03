package com.ysm.springsecurity;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings/")
public class GreetingRestController {

	
	@GetMapping(value = "/v1")
	public String getMessage(Principal principal)
	{
		return "Hello " + principal.getName()+"!!!";
	}
	
	
}
