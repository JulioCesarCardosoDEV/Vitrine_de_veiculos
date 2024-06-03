package com.juliocesar.webpage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity(name = "usuarios")
@Table(name="usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 3 , message = "O nome deve ter no mínimo 3 caracteres!")
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Email(message = "Insira um email válido!")
    @NotBlank(message = "O email é obrigatório!")
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 8 , message = "A senha deve ter no mínimo 8 caracteres!")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    public User() {

    }

    public User(Long id, String username, String email, String senha) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
