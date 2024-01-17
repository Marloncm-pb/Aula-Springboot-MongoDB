package com.compass.workshopmongo.services;

import com.compass.workshopmongo.domain.User;
import com.compass.workshopmongo.repositories.UserRepository;
import com.compass.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        User user = repo.findById(id).orElse(null);
        if(user == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }


}
