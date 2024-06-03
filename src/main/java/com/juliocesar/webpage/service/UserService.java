package com.juliocesar.webpage.service;

import com.juliocesar.webpage.dto.UserDTO;
import com.juliocesar.webpage.entities.User;
import com.juliocesar.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User insert(User obj){
        String encoder = passwordEncoder.encode(obj.getSenha());
        obj.setSenha(encoder);
        return userRepository.save(obj);
    }

    public User update(User obj) {
        String encoder = passwordEncoder.encode(obj.getSenha());
        obj.setSenha(encoder);
        User newObj = userRepository.findById(obj.getId()).get();
        updateUser(newObj, obj);
        return userRepository.save(newObj);
    }

    public void delete(Long id){
        User obj = userRepository.findById(id).get();
        userRepository.deleteById(id);
    }

    public Boolean validarSenha(User obj, Long id){
        String senha = userRepository.getById(id).getSenha();
        boolean valida = passwordEncoder.matches(obj.getSenha(), senha);
        return valida;
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
