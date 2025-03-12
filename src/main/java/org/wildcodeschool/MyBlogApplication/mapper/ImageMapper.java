package org.wildcodeschool.MyBlogApplication.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.MyBlogApplication.dto.ImageDTO;
import org.wildcodeschool.MyBlogApplication.model.Article;
import org.wildcodeschool.MyBlogApplication.model.Image;

import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public Image convertToEntity(ImageDTO imageDTO) {
        Image image = new Image();
        image.setUrl(imageDTO.getUrl());

        return image;
    }

    public ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return imageDTO;
    }

}
