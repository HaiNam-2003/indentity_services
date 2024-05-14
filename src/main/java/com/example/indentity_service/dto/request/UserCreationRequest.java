package com.example.indentity_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotNull(message = "User not null")
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
