package bootdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by David on 2017/5/19.
 */
@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 7303178895056191860L;
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "student_teacher"
            , joinColumns = @JoinColumn(name = "sid")
            , inverseJoinColumns = @JoinColumn(name = "tid"))
    private Set<Teacher> teachers;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Order> orders;
    @OneToOne(mappedBy = "student")
    private CardID cardID;

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

    public CardID getCardID() {
        return cardID;
    }

    public void setCardID(CardID cardID) {
        this.cardID = cardID;
    }
}
