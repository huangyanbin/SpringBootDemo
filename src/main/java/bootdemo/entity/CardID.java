package bootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by huang on 2017/5/20.
 * 学生证
 */
@Entity
public class CardID implements Serializable{

    private static final long serialVersionUID = 6993789070149138990L;
    @Id
    @GeneratedValue
    private long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "sid")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
