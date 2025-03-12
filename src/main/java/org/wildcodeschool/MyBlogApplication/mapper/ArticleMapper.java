package org.wildcodeschool.MyBlogApplication.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.MyBlogApplication.dto.ArticleCreateDTO;
import org.wildcodeschool.MyBlogApplication.model.*;
import org.wildcodeschool.MyBlogApplication.dto.ArticleAuthorDTO;
import org.wildcodeschool.MyBlogApplication.dto.ArticleDTO;

import java.util.stream.Collectors;


@Component
public class ArticleMapper {

    public Article convertToEntity(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article();
        article.setTitle(articleCreateDTO.getTitle());
        article.setContent(articleCreateDTO.getContent());

        return article;
    }

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            articleDTO.setImageUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
        }
        if (article.getArticleAuthors() != null) {
            articleDTO.setArticleAuthors(article.getArticleAuthors().stream()
                    .filter(articleAuthor -> articleAuthor.getAuthor() != null)
                    .map(articleAuthor -> {
                        ArticleAuthorDTO authorDTO = new ArticleAuthorDTO();
                        authorDTO.setAuthor(articleAuthor.getAuthor().getId());
                        authorDTO.setArticle(articleAuthor.getArticle().getId());
                        authorDTO.setContribution(articleAuthor.getContribution());
                        return authorDTO;
                    })
                    .collect(Collectors.toList()));
        }
        return articleDTO;
    }
}
