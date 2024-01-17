package com.compass.workshopmongo.services;

import com.compass.workshopmongo.domain.User;
import com.compass.workshopmongo.dto.UserDTO;
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

    public User insert(User user){
        return repo.insert(user);
    }

    public User fromDTO(UserDTO user){
        return new User(user.getId(), user.getName(), user.getEmail());
    }

    public User update(User user){
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return repo.save(newUser);
    }

    private void updateData(User newUser, User user){
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }


}
