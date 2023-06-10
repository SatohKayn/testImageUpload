package test.testImageUpload.repository;

import test.testImageUpload.enity.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface IBookRepository extends JpaRepository<book, Long> {
}
