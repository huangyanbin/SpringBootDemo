package bootdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by David on 2017/5/19.
 */
@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @JsonIgnore
    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "student_teacher"
    ,joinColumns = @JoinColumn(name = "sid")
    ,inverseJoinColumns = @JoinColumn(name = "tid"))
    private Set<Teacher> teachers;
    @OneToMany(mappedBy = "student",cascade={CascadeType.ALL})
    private Set<Order> orders;

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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
