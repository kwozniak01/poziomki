package com.poziomkowyspacerniak.poziomki.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private Long dogId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "color_category", nullable = false)
    private ColorCategory colorCategory;

    @Column(name = "description")
    private String description;

    public enum ColorCategory {
        czerwony, żółty, zielony
    }

}
