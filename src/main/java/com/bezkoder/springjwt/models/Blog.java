package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "blogs")
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blogTitle")
    private String blogTitle;

    @Column(name = "bookTitle")
    private String bookTitle;

    @Column(name = "posterName")
    private String posterName;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getBlogTitle() {

        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {

        this.blogTitle = blogTitle;
    }

    public String getBookTitle() {

        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {

        this.bookTitle = bookTitle;
    }

    public String getPosterName() {

        return posterName;
    }

    public void setPosterName(String posterName) {

        this.posterName = posterName;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

}
