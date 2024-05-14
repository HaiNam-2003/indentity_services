package com.example.indentity_service.controller;

import com.example.indentity_service.dto.request.UserCreationRequest;
import com.example.indentity_service.dto.response.ApiResponse;
import com.example.indentity_service.dto.response.UserResponse;
import com.example.indentity_service.entity.User;
import com.example.indentity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Them user thanh cong");
        apiResponse.setResult(userService.createRequest(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getDetailUser(@PathVariable("userId") Long userId) {
        return userService.getDetailUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") Long id, @RequestBody UserCreationRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
