package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogsRepo extends JpaRepository<Blog, Long> {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    List<Blog> findBlogByPosterName(String posterName);
    List<Blog> findBlogsByBookTitle(String bookTitle);

    @Override
    <S extends Blog> S save(S entity);
}
