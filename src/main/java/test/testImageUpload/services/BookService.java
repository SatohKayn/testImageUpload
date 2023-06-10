package test.testImageUpload.services;

import test.testImageUpload.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.testImageUpload.enity.book;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;
    public List<book> getAllBook(){
        return bookRepository.findAll();
    }
    public book getBookById(Long id){
        Optional<book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public void addBook(book newbook){
        bookRepository.save(newbook);
    }
    public void updateBook(book newbook){
        bookRepository.save(newbook);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
