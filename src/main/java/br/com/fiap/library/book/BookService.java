package br.com.fiap.library.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var book = repository.findById(id);

        if (book.isEmpty())
            return false;

        repository.deleteById(id);
        return true;
    }

}
