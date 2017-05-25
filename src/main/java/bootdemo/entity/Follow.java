package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/23.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Follow implements Serializable{

    private static final long serialVersionUID = 6317732902169713190L;
    private int id;
    private User followingUser;
    private User  followedUser;
    private Date followTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(User followingUser) {
        this.followingUser = followingUser;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}
