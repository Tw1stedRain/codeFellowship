package com.example.codefellowship.controllers;

import com.example.codefellowship.database.AppUser;
import com.example.codefellowship.database.AppUserRepo;
import com.example.codefellowship.database.Post;
import com.example.codefellowship.database.PostRepo;
import com.example.codefellowship.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    AppUserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    // create post
    @PostMapping("/newpost")
    public RedirectView newPost(
            @RequestParam String body,
            @RequestParam Date createdAt
            ) {
        Post post = new Post();
        post.setBody(body);
        post.setCreatedAt(createdAt);

        postRepo.save(post);

        return new RedirectView("/post/" + post.getId());
    }

    // view post
    @GetMapping("/post/{id}")
    public String getPost(
            @PathVariable Long id,
            Model model
    ) {
        Optional<Post> foundPost = this.postRepo.findById(id);

        if (foundPost.isPresent()) {
            model.addAttribute("post", foundPost.get());
            return "post";
        } else {
            throw new PostNotFoundException();
        }
    }


    // update post - not needed yet

    // delete post -  not needed yet


}
