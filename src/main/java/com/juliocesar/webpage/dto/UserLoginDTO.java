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
public class UserLoginDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email(message = "Insira um email válido!")
    @NotBlank(message = "O email é obrigatório!")
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 8 , message = "A senha deve ter no mínimo 8 caracteres!")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;
    public UserLoginDTO(){

    }

    public UserLoginDTO(User obj) {
        id = obj.getId();
        email = obj.getEmail();
        senha = obj.getSenha();
    }

}