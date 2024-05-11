package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer userId;
    private String username;
    private String password;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public User(String username, String password, Collection<Role> role) {
        this.username = username;
        this.password = password;
        this.roles = role;
    }
}
