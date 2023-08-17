package ru.otus.book.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@NamedEntityGraph(name = "book-genre-entity-graph",
        attributeNodes = {@NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @BatchSize(size = 5)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Comment> comments;
}
