package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/23.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TCollection implements Serializable{

    private static final long serialVersionUID = 2991955077675117002L;
    private int id ;
    private Article article;
    private CollectionType type;
    private Date collectionTime ;

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

    public CollectionType getType() {
        return type;
    }

    public void setType(CollectionType type) {
        this.type = type;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}
