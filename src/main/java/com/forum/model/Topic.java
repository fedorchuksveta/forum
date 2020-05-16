package com.forum.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name="theme_id", nullable=false)
    private Theme theme;


//    @ManyToOne
//    private Theme theme;

    @ManyToMany(mappedBy = "topicSet")
    private Set<User> userSet;

}