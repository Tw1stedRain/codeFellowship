package com.example.codefellowship.controllers;

import com.example.codefellowship.database.AppUser;
import com.example.codefellowship.database.AppUserRepo;
import com.example.codefellowship.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    AppUserRepo repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUp()
    {
        return "signin";
    }

    //POST sign up
    // save to db
    @PostMapping("/signup")
    public String postSignup(
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

        // for now, no such thing as login
        return "/login";
    }

    // login - not required at this point

    // view account (single user)
    @GetMapping("/user/{id}")
    public String getUser(
            @PathVariable Long id,
            Model model
    ) {
        Optional<AppUser> foundUser = this.repo.findById(id);

        if (foundUser.isPresent()) {
            model.addAttribute("user", foundUser.get());
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


