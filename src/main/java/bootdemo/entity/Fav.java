package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/23.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Fav implements Serializable{

    private static final long serialVersionUID = 9157293006229856113L;
    private int id ;
    private Article article;
    private User user;
    private Date favTime ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getFavTime() {
        return favTime;
    }

    public void setFavTime(Date favTime) {
        this.favTime = favTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
