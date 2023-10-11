package br.com.fiap.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", service.findAll());
        return "book/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (service.delete(id)) {
            redirect.addFlashAttribute("success", "Livro apagado com sucesso");
        } else {
            redirect.addFlashAttribute("error", "Livro não foi encontrada");
        }
        return "redirect:/book";
    }

    @GetMapping("new")
    public String form(Book book) {
        return "book/form";
    }

    @PostMapping
    public String create(@Valid Book book, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors())
            return "book/form";
        service.save(book);
        redirect.addFlashAttribute("success", "Livro cadastrada com sucesso");
        return "redirect:/book";
    }

}
