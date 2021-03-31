package br.com.marcotancredo.bookstoremanager.model.users.entity;

import br.com.marcotancredo.bookstoremanager.model.books.entity.Book;
import br.com.marcotancredo.bookstoremanager.entity.Auditable;
import br.com.marcotancredo.bookstoremanager.model.users.enums.Gender;
import br.com.marcotancredo.bookstoremanager.model.users.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;
}
