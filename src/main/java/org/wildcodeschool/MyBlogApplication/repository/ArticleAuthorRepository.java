package org.wildcodeschool.MyBlogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcodeschool.MyBlogApplication.model.ArticleAuthor;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {

}
