package org.wildcodeschool.MyBlogApplication.dto;

import org.wildcodeschool.MyBlogApplication.model.Article;

public class ArticleAuthorDTO {
    private Long id;
    private Long articleId;
    private Long  authorId;
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
