package com.impaqgroup.training.spring.security.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    public static final String POST_SEQUENCE_GENERATOR = "post_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;
}
