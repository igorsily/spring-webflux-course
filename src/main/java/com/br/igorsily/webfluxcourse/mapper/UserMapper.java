package com.br.igorsily.webfluxcourse.mapper;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request, @MappingTarget final User entity);

    UserResponse toResponse(final User user);


}
