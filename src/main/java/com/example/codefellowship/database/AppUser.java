package com.example.codefellowship.database;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue
    private long id;


    @Column(unique = true)
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;


    // one to many - posts
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public AppUser() {
        posts = new ArrayList<>();
    }

    // manytomany self-join - follows

    @OneToMany(mappedBy = "followedBy")
    private Set<UserFollow> follows;

    @OneToMany(mappedBy = "following")
    private Set<UserFollow> following;


    // overrides
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        if (this.username.equals("admin")) {
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // getters adn setters


    // id
    public long getId() {
        return this.id;
    }

    //password
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // username
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // first name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // last name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //DOB
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // bio
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // posts
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // follows
    public Set<UserFollow> getFollows() {
        return follows;
    }

    public void setFollows(Set<UserFollow> follows) {
        this.follows = follows;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }
}
