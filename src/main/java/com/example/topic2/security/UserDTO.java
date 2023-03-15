package com.example.topic2.security;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor(force = true)
public class UserDTO {
    private String username;
    private String password;

    public User toUser() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .role(User.Role.USER)
                .build();
    }
}
