package test.testImageUpload.controller;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import test.testImageUpload.enity.book;
import test.testImageUpload.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public String listBooks(Model model){
        List<book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        model.addAttribute("title", "book list");
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new book());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") book book, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "book/add";
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:/Users/nkanh/Downloads/testImageUpload/images/" + file.getOriginalFilename());
                Files.write(path, bytes);
                book.setImage("images/" + file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload the file: " + e.getMessage());
            }
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        book editBook = bookService.getBookById(id);
        if(editBook != null){
            model.addAttribute("book", editBook);
            return "book/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")book updateBook, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            return "book/edit";
        }
        bookService.getAllBook().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {

                    bookService.updateBook(updateBook);
                });
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
