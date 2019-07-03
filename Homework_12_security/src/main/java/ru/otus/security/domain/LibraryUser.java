package ru.otus.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "users")
public class LibraryUser {

    @Id
    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("roles")
    private String[] roles;

    public LibraryUser(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
