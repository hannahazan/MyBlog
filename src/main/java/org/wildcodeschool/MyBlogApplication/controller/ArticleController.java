package org.wildcodeschool.MyBlogApplication.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.MyBlogApplication.dto.ArticleCreateDTO;
import org.wildcodeschool.MyBlogApplication.model.Article;
import org.wildcodeschool.MyBlogApplication.dto.ArticleDTO;
import org.wildcodeschool.MyBlogApplication.service.ArticleService;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search-content")
    public ResponseEntity<List<ArticleDTO>> getArticlesByContent(@RequestParam String searchTerms) {
        List<ArticleDTO> articles = articleService.getArticlesByContentContaining(searchTerms);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/start-after")
    public ResponseEntity<List<ArticleDTO>> getArticlesAfterDate(@RequestParam LocalDateTime date) {
        List<ArticleDTO> articles = articleService.getArticlesCreatedAfter(date);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/Last-five-articles")
    public ResponseEntity<List<ArticleDTO>> getLastFiveArticles() {
        List<ArticleDTO> articles = articleService.getLastFiveArticlesOrdered();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        ArticleDTO article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

   @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleCreateDTO article) {

       ArticleDTO savedArticle = articleService.createArticle(article);

       return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

        ArticleDTO updatedArticle = articleService.updateArticle(id,articleDetails);

        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
