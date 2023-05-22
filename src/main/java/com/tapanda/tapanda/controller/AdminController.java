package com.tapanda.tapanda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tapanda.tapanda.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;

	@GetMapping("/admin")
	public String main() {
		return "admin/index";
	}
	
	@GetMapping("/")
	public String testreact() {
		return "index";
	}
	
	@GetMapping("/admin/users")
	public String user() {
		return "admin/user";
	}
	
	@GetMapping("/admin/userinfo/{id}")
	public String userInfo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("isSeller", userService.isSeller(id));
		return "admin/userInfo";
	}
	
	@GetMapping("/admin/userinfo-edit/{id}")
	public String userInfoEdit() {
		return "admin/userInfoEdit";
	}
	
	@GetMapping("/admin/userprofile-edit/{id}")
	public String userProfileEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("isSeller", userService.isSeller(id));
		return "admin/userProfileEdit";
	}
	
	@GetMapping("/admin/user-register")
	public String userRegister() {
		return "admin/userInfoRegister";
	}
}