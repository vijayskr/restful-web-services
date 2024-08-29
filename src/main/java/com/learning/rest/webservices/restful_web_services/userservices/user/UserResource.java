package com.learning.rest.webservices.restful_web_services.userservices.user;

import com.learning.rest.webservices.restful_web_services.userservices.dao.UserDAOService;
import com.learning.rest.webservices.restful_web_services.userservices.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDAOService userService;

    public UserResource(UserDAOService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{userID}")
    public User retrieveUser(@PathVariable int userID) {
        User user = userService.findByID(userID);
        if(user == null)
            throw new UserNotFoundException("User ID :: " + userID);
        else
            return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);

        //Return URI for better usage and checking details
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        //Returning the proper code
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{userID}")
    public void deleteUser(@PathVariable int userID) {
        userService.deleteById(userID);
    }
}
