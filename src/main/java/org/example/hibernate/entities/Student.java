package org.example.hibernate.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
// if here is not used table annotation then it will take class name as table name in db
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(name = "student_name", length = 100, unique = true)// it will used as column name
    private String name;

    @Column(name = "student_college", length = 200, nullable = true)
    private String college;
    private String phone;
    private String fathername;
    private boolean active = true;

    //@Lob used to large object time. Use when we don't know max size
    @Lob
    private String about;

    // Cascade All : when we save/delete student then its certificates also saved/delete in db no need to separate save certificate
    // orphanRemoval : when we get student list then if we remove any certificate from list then its that certificate also get removed
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)// Eager fatch certificate if on get student
    private List<Certificate> certificateList = new ArrayList<>();

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
