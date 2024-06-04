package com.juliocesar.webpage.controller;

import com.juliocesar.webpage.dto.UserDTO;
import com.juliocesar.webpage.entities.User;
import com.juliocesar.webpage.security.Token;
import com.juliocesar.webpage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDTO){
        User obj = userService.fromDTO(objDTO);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Token> logar(@Valid @RequestBody UserDTO objDTO) {
        Token token = userService.gerarToken(objDTO);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(403).build();
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody UserDTO objDTO) {
        User obj = userService.fromDTO(objDTO);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
