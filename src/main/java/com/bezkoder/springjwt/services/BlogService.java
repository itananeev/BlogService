package com.bezkoder.springjwt.services;

import com.bezkoder.springjwt.models.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    List<Blog> findBlogByPosterName(String posterName);
    List<Blog> findBlogsByBookName(String bookName);

    Blog createBlog(Blog b);
    Blog updateBlog(Long id, Blog b);
    void deleteBlog(Long id);
    boolean exists(Long id);
}
