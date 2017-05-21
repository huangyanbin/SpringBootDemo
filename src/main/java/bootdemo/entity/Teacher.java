package bootdemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by David on 2017/5/19.
 */
@Entity
public class Teacher implements Serializable{

    private static final long serialVersionUID = 8767102407432935440L;
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @ManyToMany(mappedBy = "teachers",cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Set<Student> students;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
