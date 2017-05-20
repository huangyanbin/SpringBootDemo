package bootdemo.entity;

import javax.persistence.*;

/**
 * Created by huang on 2017/5/20.
 * 学生证
 */
@Entity
public class CardID {

    @Id
    @GeneratedValue
    private long id;

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
