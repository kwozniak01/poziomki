package com.poziomkowyspacerniak.poziomki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "volunteers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    private Long volunteerId;

    @NotNull
    @Column(name = "username", unique = true)  // Dodano unikalność i obowiązkowość
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    public enum ColorCategory {
        czerwony, żółty, zielony
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "color_category")
    private ColorCategory colorCategory;
}
