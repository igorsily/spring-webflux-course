package com.br.igorsily.webfluxcourse.model.request;

import com.br.igorsily.webfluxcourse.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @TrimString(
                groups = {
                        UserRequestView.UserRequestCreateView.class
                }
        )
        @Size(min = 2, max = 200, message = "must be between 2 and 200 characters",
                groups = {
                        UserRequestView.UserRequestCreateView.class
                })
        @NotBlank(message = "must not be null or empty",
                groups = {
                        UserRequestView.UserRequestCreateView.class
                })
        String name,

        @TrimString(groups = {
                UserRequestView.UserRequestCreateView.class,
                UserRequestView.UserRequestUpdateView.class
        })
        @Email(message = "must be a valid email",
                groups = {
                        UserRequestView.UserRequestCreateView.class,
                        UserRequestView.UserRequestUpdateView.class
                }
        )
        @NotBlank(message = "must not be null or empty",
                groups = {
                        UserRequestView.UserRequestCreateView.class,
                        UserRequestView.UserRequestUpdateView.class
                }
        )
        String email,
        @TrimString(
                groups = {
                        UserRequestView.UserRequestCreateView.class
                }
        )
        @Size(min = 2, max = 200, message = "must be between 2 and 200 characters",
                groups = {
                        UserRequestView.UserRequestCreateView.class
                })
        @NotBlank(message = "must not be null or empty",
                groups = {
                        UserRequestView.UserRequestCreateView.class
                })
        String password
) {

    public interface UserRequestView {


        interface UserRequestCreateView {
        }

        interface UserRequestUpdateView {
        }
    }
}
