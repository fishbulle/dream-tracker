package com.angelina.dreamtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dreams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dream {

    @Id
    private UUID id;
    private String title;
    private String content;
    private String category;

    // instead of oneToMany (one dream can have several tags)
    @ElementCollection
    private List<String> tags;

    @ManyToOne
    private User user;
}
