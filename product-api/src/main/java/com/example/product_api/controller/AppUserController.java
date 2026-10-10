package com.example.product_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.product_api.entity.AppUser;
import com.example.product_api.service.AppUserService;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

	private final AppUserService appUserService;

	public AppUserController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@PostMapping
	public AppUser createUser(@RequestBody AppUser user) {
		return appUserService.createUser(user);
	}

	@GetMapping
	public List<AppUser> getAllUsers() {
		return appUserService.getAllUsers();
	}

	@GetMapping("/{id}")
	public AppUser getUserById(@PathVariable Long id) {
		return appUserService.getUserById(id);
	}

	@PutMapping("/{id}")
	public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user) {
		return appUserService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return "User deleted successfully";
	}
}
