package org.wildcodeschool.MyBlogApplication.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.wildcodeschool.MyBlogApplication.model.Article;
import jakarta.annotation.sql.DataSourceDefinition;

public class ArticleAuthorDTO {
    private Long id;

    private Long articleId;

    @NotNull(message = "L'Id de l'auteur ne doit pas être null")
    @Positive(message = "L'Id de l'auteur ne doit pas être un nombre positif")
    private Long  authorId;

    @NotBlank(message = "la contribution de l'auteur ne doit pas être vide")
    private String contribution;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticle(Long articleId) {
        this.articleId = articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthor(Long authorId) {
        this.authorId = authorId;
    }

    public String getContribution(){
        return contribution;
    }

    public void setContribution(String contribution){
        this.contribution = contribution;
    }
}
