package com.dev.ecommerce.controller;

import com.dev.ecommerce.service.PhotoService;
import com.dev.ecommerce.service.UserService;
import com.dev.ecommerce.dto.request.UserRequestDTO;
import com.dev.ecommerce.dto.response.UserResponseDTO;
import com.dev.ecommerce.dto.response.UserListResponseDTO;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	private final PhotoService photoService;

	public UserController(UserService userService, PhotoService photoService) {
		this.userService = userService;
		this.photoService = photoService;
	}

	@GetMapping
	public ResponseEntity<List<UserListResponseDTO>> listAllUsers() {
		return ResponseEntity.ok(userService.listAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> listUserById(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(userService.listUserById(userId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

//	@PostMapping
//	public ResponseEntity<UserResponseDTO> createUser(
//		@RequestBody @Valid UserRequestDTO userRequestDTO) {
//
//		return ResponseEntity.status(HttpStatus.CREATED)
//			.body(userService.createUser(userRequestDTO));
//	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestParam String name,
	@RequestParam String email, @RequestParam String phoneNumber,
	@RequestParam String password,
	@RequestParam MultipartFile photo) throws IOException {
		String photoPath = photoService.savePhoto(photo);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(userService.createUser(name, photoPath, email, phoneNumber, password));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
		@RequestBody @Valid UserRequestDTO userRequestDTO) {

		try {
			return ResponseEntity.ok()
				.body(userService.updateUser(userId, userRequestDTO));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (userService.deleteUser(userId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}
}
