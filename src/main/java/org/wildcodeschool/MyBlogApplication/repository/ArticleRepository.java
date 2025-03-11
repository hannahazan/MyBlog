package org.wildcodeschool.MyBlogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wildcodeschool.MyBlogApplication.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findByContentContaining(String content);
    List<Article> findByCreatedAtAfter(LocalDateTime dte);
    List<Article> findByTitle(String title);
    @Query(value="SELECT * FROM article ORDER BY created_at DESC LIMIT 5",nativeQuery = true)
    List<Article> findTheLastFive();
}
