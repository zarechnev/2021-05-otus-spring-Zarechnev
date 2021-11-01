package ru.otus.zarechnev.library.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    @Fetch(FetchMode.SELECT)
    private Genre genre;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @Fetch(FetchMode.SELECT)
    private Author author;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Comment> comments;
}
