package org.wildcodeschool.MyBlogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcodeschool.MyBlogApplication.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
