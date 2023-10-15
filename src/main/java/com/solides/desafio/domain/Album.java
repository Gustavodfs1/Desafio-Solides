package com.solides.desafio.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_id_seq")
    @SequenceGenerator(name = "albums_id_seq", sequenceName = "albums_id_seq", allocationSize = 1)
    private Integer id;

    private String nameAlbum;

   @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "album")
    private Set<Photo> photos;
}
