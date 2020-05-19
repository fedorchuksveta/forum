package com.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private String name;

    @ManyToMany(mappedBy = "themeSet")
    @ToString.Exclude
    private Set<User> userSet;

    @OneToMany(mappedBy = "theme")
    @ToString.Exclude
    private Set<Topic> topicSet;

}