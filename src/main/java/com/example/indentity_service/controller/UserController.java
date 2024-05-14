package com.example.indentity_service.controller;

import com.example.indentity_service.dto.request.UserCreationRequest;
import com.example.indentity_service.dto.response.ApiResponse;
import com.example.indentity_service.dto.response.UserResponse;
import com.example.indentity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
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
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication(); // lay thong tin user dang login

        log.info("Username: {}", authentication.getName()); // log ra username
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority())); // log ra roles cua user dang login

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .code(200)
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getDetailUser(@PathVariable("userId") Long userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getDetailUser(userId))
                .code(200)
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .code(200)
                .build();
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
