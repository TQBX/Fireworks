package com.smday.fireworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;

/**
 * created by Summer-day
 */
@Controller
public class T {
	
	@RequestMapping("/test")
	public String test(){
		return "";
	}
	
}
