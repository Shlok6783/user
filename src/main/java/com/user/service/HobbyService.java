package com.user.service;

import com.user.model.Hobby;
import com.user.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    public Hobby getHobbyById(int id) {
        return hobbyRepository.findById(id).orElse(null);
    }

    public void saveOrUpdateHobby(Hobby hobby) {
        hobbyRepository.save(hobby);
    }

    public void deleteHobby(int id) {
        hobbyRepository.deleteById(id);
    }
}
