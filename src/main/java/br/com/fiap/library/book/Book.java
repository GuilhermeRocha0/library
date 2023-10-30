package br.com.fiap.library.book;

import java.util.List;

import br.com.fiap.library.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String title;

    @NotBlank
    String author;

    @Size(min = 10, max = 1000, message = "{book.synopsis.size}")
    String synopsis;

    @NotNull
    @Positive
    @Min(1)
    Integer quantity;

    @NotNull
    @Min(0)
    Integer inStock;

    @OneToMany
    private List<User> users;

}
