package application.com.services;

import application.com.entities.Post;
import application.com.repositories.PostRepository;
import application.com.utils.IterableToList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public List<Post> findAll(){

        List<Post> posts = IterableToList.convert(repository.findAll());

        if (posts.isEmpty()){
            throw new IllegalArgumentException("No one post found");
        }

        return posts;
    }


}
