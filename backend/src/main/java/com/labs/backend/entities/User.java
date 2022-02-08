package com.labs.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private String login;

    private String password;
    private byte[] salt;

    public User(String login, String password, byte[] salt) {
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return login;
    }
}
