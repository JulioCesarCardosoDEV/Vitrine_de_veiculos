package com.juliocesar.webpage.service;

import com.juliocesar.webpage.dto.UserDTO;
import com.juliocesar.webpage.entities.User;
import com.juliocesar.webpage.repository.UserRepository;
import com.juliocesar.webpage.security.Token;
import com.juliocesar.webpage.security.util.TokenUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> findAll(){
        logger.info("Usuario: " + getLogado() + " Listando Usuários");
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User insert(User obj){
        String encoder = passwordEncoder.encode(obj.getSenha());
        obj.setSenha(encoder);
        logger.info("Usuario: " + getLogado() + " Criando Usuário");
        return userRepository.save(obj);
    }

    public User update(User obj) {
        String encoder = passwordEncoder.encode(obj.getSenha());
        obj.setSenha(encoder);
        User newObj = userRepository.findById(obj.getId()).get();
        updateUser(newObj, obj);
        logger.info("Usuario: " + getLogado() + " Editando Usuário " + obj.getUsername());
        return userRepository.save(newObj);
    }

    public void delete(Long id){
        logger.info("Usuario: " + getLogado() + " Excluindo Usuário");
        userRepository.deleteById(id);
    }

    public Token gerarToken(@Valid UserDTO usuario) {
        User user = userRepository.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());
        if (user != null) {
            boolean valid = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
            if (valid) {
                return new Token(TokenUtil.createToken(user));
            }
        }
        return null;
    }

    private String getLogado(){
        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        if(!(userLogado instanceof AnonymousAuthenticationToken)){
            return userLogado.getName();
        }
        return null;
    }

    private void updateUser(User newObj, User obj) {
        newObj.setUsername(obj.getUsername());
        newObj.setEmail(obj.getEmail());
        newObj.setSenha(obj.getSenha());
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getUsername(), objDTO.getEmail(), objDTO.getSenha());
    }
}
