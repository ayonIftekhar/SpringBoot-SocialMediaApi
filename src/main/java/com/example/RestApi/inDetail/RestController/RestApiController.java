package com.example.RestApi.inDetail.RestController;


import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.example.RestApi.inDetail.DbController.PostRepository;
import com.example.RestApi.inDetail.DbController.UserRepository;
import com.example.RestApi.inDetail.Exceptions.UserNotFoundException;
import com.example.RestApi.inDetail.UserDetails.Post;
import com.example.RestApi.inDetail.UserDetails.User;
import com.example.RestApi.inDetail.UserDetails.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{name}")
    public EntityModel<User> getParticularUsers(@PathVariable String name)
            throws UserNotFoundException {

        User users = userRepository.findByName(name);
        if(users == null)
            throw new UserNotFoundException(name + " Not Found");

        EntityModel<User> entityModel = EntityModel.of(users);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()
        );
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping(path = "/users")
    public ResponseEntity addNewUser(@Valid @RequestBody User user){

        userRepository.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{name}").
                buildAndExpand(user.getName()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "users/{name}")
    public void deleteUser(@PathVariable String name){
        userRepository.deleteByName(name);
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getAllPosts(@PathVariable int id)
            throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("no user with id : " +id);

        return postRepository.findByUser(user.get());
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id,
                                     @RequestBody Post post)
            throws UserNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("no user with id : " +id);

        post.setUser(user.get());
        postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("").build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/users/{id}/posts/{post_id}")
    public void deletePost(@PathVariable int id,
                           @PathVariable int post_id){
        postRepository.deleteById(post_id);
    }

}
