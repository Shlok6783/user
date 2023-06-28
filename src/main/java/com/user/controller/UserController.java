package com.user.controller;

import com.user.model.Hobby;
import com.user.model.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    
   
    @GetMapping("/users/{userId}/hobbies")
    public List<Hobby> getHobbiesByUserId(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
        	return user.getHobbies();
        }
        return Collections.emptyList();
    }

        

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        user.setId(id);
        userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
    
    @DeleteMapping("/users/{userId}/hobbies/{hobbyId}")
    public ResponseEntity<String> removeHobbyFromUser(
            @PathVariable("userId") int userId,
            @PathVariable("hobbyId") int hobbyId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            List<Hobby> hobbies = user.getHobbies();
            Hobby hobbyToRemove = null;
            for (Hobby hobby : hobbies) {
                if (hobby.getId() == hobbyId) {
                    hobbyToRemove = hobby;
                    break;
                }
            }
            if (hobbyToRemove != null) {
                user.removeHobby(hobbyToRemove);
                userService.saveOrUpdateUser(user);
                return ResponseEntity.ok("Hobby removed from user");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
