package org.wildcodeschool.MyBlogApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.MyBlogApplication.dto.AuthorDTO;
import org.wildcodeschool.MyBlogApplication.model.Author;
import org.wildcodeschool.MyBlogApplication.repository.AuthorRepository;
import org.wildcodeschool.MyBlogApplication.dto.ArticleAuthorDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AuthorDTO> authorDTOs = authors.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(author));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(201).body(convertToDTO(savedAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        author.setFirstname(authorDetails.getFirstname());
        author.setLastname(authorDetails.getLastname());

        Author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(convertToDTO(updatedAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        authorRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstname(author.getFirstname());
        authorDTO.setLastname(author.getLastname());
        authorDTO.setArticleAuthors(author.getArticleAuthors().stream()
                .filter(articleAuthor -> articleAuthor.getArticle() != null)
                .map(articleAuthor -> {
                    ArticleAuthorDTO articleAuthorDTO = new ArticleAuthorDTO();
                    articleAuthorDTO.setAuthor(articleAuthor.getAuthor().getId());
                    articleAuthorDTO.setArticle(articleAuthor.getArticle().getId());
                    articleAuthorDTO.setContribution(articleAuthor.getContribution());
                    return articleAuthorDTO;
                })
                .collect(Collectors.toList()));
        return authorDTO;
    }

}
