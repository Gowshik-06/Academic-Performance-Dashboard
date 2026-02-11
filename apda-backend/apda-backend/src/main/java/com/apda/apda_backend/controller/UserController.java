package com.apda.apda_backend.controller;

import com.apda.apda_backend.dto.CreateUserRequest;
import com.apda.apda_backend.dto.UserDTO;
import com.apda.apda_backend.entity.User;
import com.apda.apda_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateUserRequest request) {

        User user = userService.createUser(request); // ENTITY
        return mapToDTO(user);                        // DTO
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);
        return mapToDTO(user);
    }

    @GetMapping("/test")
    public String test() {
        return "User API is working";
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole()
        );
    }
}
