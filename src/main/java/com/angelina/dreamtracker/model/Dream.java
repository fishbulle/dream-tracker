package com.angelina.dreamtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String content;
    private String category;

    // instead of oneToMany (one dream can have several tags)
/*    @ElementCollection
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<String> tags;*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
