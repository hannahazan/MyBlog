package org.wildcodeschool.MyBlogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcodeschool.MyBlogApplication.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
