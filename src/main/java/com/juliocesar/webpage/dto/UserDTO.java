package com.juliocesar.webpage.dto;

import com.juliocesar.webpage.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO{

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
    public UserDTO(){

    }

    public UserDTO(User obj) {
        id = obj.getId();
        username = obj.getUsername();
        email = obj.getEmail();
        senha = obj.getSenha();
    }

}