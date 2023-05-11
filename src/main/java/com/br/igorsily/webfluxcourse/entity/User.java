package com.br.igorsily.webfluxcourse.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    private String name;

    @Indexed(name = "email_index", unique = true)
    private String email;

    private String password;
}
