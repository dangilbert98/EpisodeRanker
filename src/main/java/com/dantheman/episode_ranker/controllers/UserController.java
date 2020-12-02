package com.dantheman.episode_ranker.controllers;

import com.dantheman.episode_ranker.exceptions.EntityExistsException;
import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Episode;
import com.dantheman.episode_ranker.models.Show;
import com.dantheman.episode_ranker.models.User;
import com.dantheman.episode_ranker.services.EpisodesService;
import com.dantheman.episode_ranker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam String username)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestParam String username, @RequestParam String password)
            throws EntityExistsException {
        System.out.println("adding");
        userService.addUser(username, password);
        System.out.println("user: " + username + " added");
        return ResponseEntity.ok(username);
    }
}
