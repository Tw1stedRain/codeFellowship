package com.example.codefellowship.controllers;

import com.example.codefellowship.database.AppUser;
import com.example.codefellowship.database.AppUserRepo;
import com.example.codefellowship.database.Post;
import com.example.codefellowship.database.PostRepo;
import com.example.codefellowship.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    AppUserRepo repo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUp() {
        return "signin";
    }

    //POST sign up
    // save to db
    @PostMapping("/signup")
    public RedirectView postSignup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String bio
    ) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setBio(bio);

        repo.save(user);

        // auto login
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(token);

        String redirect = "/user/" + user.getId();
        return new RedirectView(redirect);
    }

    // login
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/myprofile")
    public RedirectView myProfile(
            Model model,
            Principal principal
    ) {
        AppUser user = repo.findByUsername(principal.getName());
        return new RedirectView("/user/" + user.getId());
    }


    // /myprofile
    @GetMapping("/user/{id}")
    public String getUser(
            @PathVariable Long id,
            Model model
    ) {
        Optional<AppUser> foundUser = this.repo.findById(id);

        if (foundUser.isPresent()) {
            AppUser user = foundUser.get();

            List<Post> posts = user.getPosts();

            model.addAttribute("user", user);
            model.addAttribute("posts", posts);

            return "user";
        } else {
            throw new UserNotFoundException();
        }
    }


    // login error
    @GetMapping("/login-error")
    @ResponseBody
    public String getLoginError() {
        return "Bad Username/Password";
    }

}


