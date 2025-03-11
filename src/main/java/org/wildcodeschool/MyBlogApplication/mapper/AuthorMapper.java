package org.wildcodeschool.MyBlogApplication.mapper;

import org.wildcodeschool.MyBlogApplication.dto.ArticleAuthorDTO;
import org.wildcodeschool.MyBlogApplication.dto.AuthorDTO;
import org.wildcodeschool.MyBlogApplication.model.Author;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    public AuthorDTO convertToDTO(Author author) {
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
