package com.user.controller;

import com.user.model.Hobby;
import com.user.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @GetMapping("/hobbies")
    public List<Hobby> getAllHobbies() {
        return hobbyService.getAllHobbies();
    }

    @GetMapping("/hobbies/{id}")
    public Hobby getHobby(@PathVariable("id") int id) {
        return hobbyService.getHobbyById(id);
    }

    @PostMapping("/hobbies")
    public void createHobby(@RequestBody Hobby hobby) {
        hobbyService.saveOrUpdateHobby(hobby);
    }

    @PutMapping("/hobbies/{id}")
    public void updateHobby(@PathVariable("id") int id, @RequestBody Hobby hobby) {
        hobby.setId(id);
        hobbyService.saveOrUpdateHobby(hobby);
    }

    @DeleteMapping("/hobbies/{id}")
    public void deleteHobby(@PathVariable("id") int id) {
        hobbyService.deleteHobby(id);
    }
}
