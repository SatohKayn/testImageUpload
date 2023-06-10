package test.testImageUpload.enity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "book")
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotEmpty(message = "Title must not empty")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Author must not empty")
    private String author;

    @Column(name = "price")
    @NotNull(message = "price is required")
    private double price;

    @Column(name = "image")
    private String image;
}
