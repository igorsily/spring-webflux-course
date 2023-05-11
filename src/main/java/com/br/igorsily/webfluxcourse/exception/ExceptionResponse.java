package com.br.igorsily.webfluxcourse.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private String path;

    private String method;

    @Builder.Default
    private List<ErrorValidation> errorValidationList = new ArrayList<>();
}
