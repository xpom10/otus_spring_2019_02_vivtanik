package ru.otus.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Data
@SuppressWarnings("deprecation")
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

    public static UserDetails toUserDetails(LibraryUser libraryUser) {
        return User.withUsername(libraryUser.getUsername())
                .password(NoOpPasswordEncoder.getInstance().encode(libraryUser.getPassword()))
                .roles(libraryUser.getRoles()).build();

    }
}
