package org.wildcodeschool.MyBlogApplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String firstname;

    @Column(nullable = false, length = 150)
    private String lastname;

    @OneToMany(mappedBy ="author")
    private List<ArticleAuthor> articleAuthors;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public List<ArticleAuthor> getArticleAuthors() {
        return articleAuthors;
    }

    public void setArticleAuthors(List<ArticleAuthor> articleAuthors) {
        this.articleAuthors = articleAuthors;
    }
}
