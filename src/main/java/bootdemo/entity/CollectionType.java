package bootdemo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/23.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CollectionType implements Serializable {

    private static final long serialVersionUID = 2479046098447398530L;
    private int id;
    private User user;
    private String type;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
