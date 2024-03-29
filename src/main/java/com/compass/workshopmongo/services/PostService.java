package com.compass.workshopmongo.services;

import com.compass.workshopmongo.domain.Post;
import com.compass.workshopmongo.domain.User;
import com.compass.workshopmongo.repositories.PostRepository;
import com.compass.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Post post = repo.findById(id).orElse(null);
        if (post == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }

    public List<Post> findByTitle(String text){
        return repo.searchTitle(text);
    }

    public List<Post> findAll() {
        return repo.findAll();
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); //Adiciona 1 dia em milisegundos
        return repo.fullSearch(text, minDate, maxDate);
    }
}
