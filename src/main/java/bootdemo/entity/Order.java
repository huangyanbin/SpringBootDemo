package bootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by huang on 2017/5/19.
 */
@Entity
@Table(name = "myOrder")
public class Order implements Serializable {

    private static final long serialVersionUID = -1815918688795381802L;
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne(cascade =CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sid")
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
