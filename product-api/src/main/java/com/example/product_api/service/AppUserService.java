package com.example.product_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_api.entity.AppUser;
import com.example.product_api.repository.AppUserRepository;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	public AppUser createUser(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll();
	}

	public AppUser getUserById(Long id) {
		return appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found " + id));
	}

	public AppUser updateUser(Long id, AppUser updatedUser) {
		AppUser existing = getUserById(id);

		existing.setName(updatedUser.getName());
		existing.setEmail(updatedUser.getEmail());

		return appUserRepository.save(existing);
	}

	public void deleteUser(Long id) {
		AppUser user = getUserById(id);
		appUserRepository.delete(user);
	}

}
