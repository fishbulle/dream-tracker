package com.angelina.dreamtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private UUID id;

    @Column(unique = true)
    private String email;

    //private String password;
    private String firstName;
    private String lastName;

    // @OneToMany
    // private List<Dream> dreams;
}
