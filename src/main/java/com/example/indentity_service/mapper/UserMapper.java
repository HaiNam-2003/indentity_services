package com.example.indentity_service.mapper;

import com.example.indentity_service.dto.request.UserCreationRequest;
import com.example.indentity_service.dto.response.UserResponse;
import com.example.indentity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "firstName", target = "lastName")
//    map firstName giống với lastName
//    @Mapping(source = "firstName", ignore = true)
//    không map field firstName -> firstName = null
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserCreationRequest request);
}
