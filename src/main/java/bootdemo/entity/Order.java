package bootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by David on 2017/5/19.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @JsonIgnore
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "sid",referencedColumnName="id")
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
