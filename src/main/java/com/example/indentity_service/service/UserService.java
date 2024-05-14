package com.example.indentity_service.service;

import com.example.indentity_service.dto.request.UserCreationRequest;
import com.example.indentity_service.dto.response.UserResponse;
import com.example.indentity_service.entity.User;
import com.example.indentity_service.exception.AppException;
import com.example.indentity_service.exception.ErrorCode;
import com.example.indentity_service.mapper.UserMapper;
import com.example.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public UserResponse createRequest(UserCreationRequest request) {

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);

        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getDetailUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.toUserResponse(userOptional.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UserResponse updateUser(Long userId, UserCreationRequest request) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userMapper.updateUser(user, request);
            return userMapper.toUserResponse(userRepository.save(user));
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
