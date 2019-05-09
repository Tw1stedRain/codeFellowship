package com.example.codefellowship.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {


    AppUser findByUsername(String username);
}
