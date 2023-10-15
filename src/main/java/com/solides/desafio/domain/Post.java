package com.solides.desafio.domain;


import com.solides.desafio.constants.TypePost;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_seq")
    @SequenceGenerator(name = "posts_id_seq", sequenceName = "posts_id_seq", allocationSize = 1)
    private Integer id;

    private String value;

    @Enumerated(EnumType.STRING)
    private TypePost type;

    @Column(name = "date_post")
    private LocalDateTime datePost;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "posts_id", referencedColumnName = "id")
    private Post post;

    @OneToMany(mappedBy = "post")
    private Set<Post> comments;
}
