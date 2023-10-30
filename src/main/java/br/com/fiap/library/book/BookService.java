package br.com.fiap.library.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.library.user.User;
import lombok.var;

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

    public void save(Book book) {
        book.setInStock(book.getQuantity());
        repository.save(book);
    }

    public void decrement(Long id) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Livro não encontrado");

        var book = opt.get();
        if (book.getInStock() == 0)
            throw new RuntimeException("Quantidade em estoque não pode ser negativa");

        book.setInStock(book.getInStock() - 1);
        repository.save(book);
    }

    public void increment(Long id) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Livro não encontrado");

        var book = opt.get();
        if (book.getInStock() == book.getQuantity())
            throw new RuntimeException("Quantidade em estoque não pode ser maior que a quantidade registrada");

        book.setInStock(book.getInStock() + 1);
        repository.save(book);
    }

    public void catchBook(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Livro não encontrado");

        var book = opt.get();
        System.out.println(book);
        System.out.println(user);

        if (book.getUsers().contains(user))
            throw new RuntimeException("Você já pegou este livro emprestado");

        if (book.getInStock() == 0)
            throw new RuntimeException("Todos os livros já estão emprestados");

        book.getUsers().add(user);
        repository.save(book);
    }

    public void dropBook(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Livro não encontrado");

        var book = opt.get();

        book.getUsers().remove(user);
        repository.save(book);
    }
}
