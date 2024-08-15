package com.example.RestApi.inDetail.DbController;

import com.example.RestApi.inDetail.UserDetails.Post;
import com.example.RestApi.inDetail.UserDetails.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends
        JpaRepository<Post,Integer> {

        public List<Post> findByUser(User user);
}
