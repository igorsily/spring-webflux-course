package com.br.igorsily.webfluxcourse.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Size(min = 2, max = 200, message = "must be between 2 and 200 characters")
        @NotBlank(message = "must not be null or empty")
        String name,

        @Email(message = "must be a valid email")
        @NotBlank(message = "must not be null or empty")
        String email,

        @Size(min = 2, max = 200, message = "must be between 2 and 200 characters")
        @NotBlank(message = "must not be null or empty")
        String password
) {
}
