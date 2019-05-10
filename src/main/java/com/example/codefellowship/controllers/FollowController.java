package com.example.codefellowship.controllers;

import com.example.codefellowship.database.AppUser;
import com.example.codefellowship.database.AppUserRepo;
import com.example.codefellowship.database.UserFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    AppUserRepo userRepo;

    @Autowired
    UserFollowRepo followRepo;

    // new follow
    @PostMapping("/")
    public RedirectView newFollow(
            @AuthenticationPrincipal AppUser user,
            @RequestParam long followTarget
    ) {
        AppUser appUser = userRepo.findById(user.getId()).get();
        AppUser followTargetId = userRepo.findById(followTarget).get();

        appUser.setFollowing(followTargetId);
    }

    // unfollow (delete)

}
