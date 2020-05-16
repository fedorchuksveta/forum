package com.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String schedule;
    private String name;


//    @OneToMany
//    private Topic topic;


    @ManyToMany(mappedBy = "themeSet")
    private Set<User> userSet;

    @OneToMany(mappedBy="theme")
    private Set<Topic> topicSet;

}