package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;

/**
 * Created by huang on 2017/5/21.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Article{
    private int id;
    private User user;
    private String title;
    private String url;
    private String icon;
    private String content;
    private Date createTime;
    private ArticleType type;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
