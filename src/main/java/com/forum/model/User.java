package com.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String surName;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topic_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    @ToString.Exclude
    private Set<Topic> topicSet;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "theme_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @ToString.Exclude
    private Set<Theme> themeSet;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> commentList;
}