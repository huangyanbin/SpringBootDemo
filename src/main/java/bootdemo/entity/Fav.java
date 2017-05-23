package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;

/**
 * Created by David on 2017/5/23.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Fav {

    private int id ;
    private Article article;
    private FavType type;
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

    public FavType getType() {
        return type;
    }

    public void setType(FavType type) {
        this.type = type;
    }

    public Date getFavTime() {
        return favTime;
    }

    public void setFavTime(Date favTime) {
        this.favTime = favTime;
    }
}
