package com.example.haisya_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@GetMapping("/top")
	public String top() {
		return "admin/top";
	}
	
}
