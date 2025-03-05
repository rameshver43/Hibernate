package org.example.hibernate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student_certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long certificateId;
    private String title;
    private String about;
    private String link;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
