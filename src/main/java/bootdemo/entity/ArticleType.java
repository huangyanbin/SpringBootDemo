package bootdemo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/23.
 */
public class ArticleType implements Serializable{

    private static final long serialVersionUID = -5800925454234667282L;
    private int id;
    private String title;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
