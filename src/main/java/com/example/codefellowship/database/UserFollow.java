package com.example.codefellowship.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserFollow {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private AppUser followers;

    @ManyToOne
    private AppUser following;

    public UserFollow() {

    }

    public UserFollow(AppUser to, AppUser from) {
        this.followers = to;
        this.following = from;
    }

    public long getId() {
        return id;
    }

    public AppUser getFollowers() {
        return followers;
    }

    public void setFollowers(AppUser followers) {
        this.followers = followers;
    }

    public AppUser getFollowing() {
        return following;
    }

    public void setFollowing(AppUser following) {
        this.following = following;
    }
}
