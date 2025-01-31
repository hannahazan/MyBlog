package org.wildcodeschool.MyBlogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcodeschool.MyBlogApplication.model.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {


}
