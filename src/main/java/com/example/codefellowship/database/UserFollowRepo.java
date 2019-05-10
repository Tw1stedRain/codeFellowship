package com.example.codefellowship.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepo extends JpaRepository<UserFollow, Long> {
}
