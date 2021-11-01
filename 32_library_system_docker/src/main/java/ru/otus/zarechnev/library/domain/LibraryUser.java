package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "Library_Users")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "authority", nullable = false)
    private String authority;
}
