package com.bezkoder.springjwt.services;

import com.bezkoder.springjwt.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.springjwt.repository.BlogsRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImplementation implements BlogService {
    @Autowired
    private final BlogsRepo blogsRepo;

    public BlogServiceImplementation(BlogsRepo blogsRepo){
        this.blogsRepo = blogsRepo;
    }

    @Override
    public List<Blog> findAll(){
        return blogsRepo.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogsRepo.findById(id); }

    @Override
    public List<Blog> findBlogByPosterName(String posterName) {
        return blogsRepo.findBlogByPosterName(posterName); }

    @Override
    public List<Blog> findBlogsByBookName(String bookTitle){
        return blogsRepo.findBlogsByBookTitle(bookTitle);
    }

    @Override
    public Blog createBlog(Blog b){
        return blogsRepo.save(b);
    }

    @Override
    public Blog updateBlog(Long id, Blog b){
        Optional<Blog> blog = findById(id);
        if(blog.isPresent()){
            blog.get().setDescription(b.getDescription());
            blog.get().setBlogTitle(b.getBlogTitle());
            blog.get().setContent(b.getContent());
            return blogsRepo.save(blog.get());
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteBlog(Long id){
        blogsRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id){
        return blogsRepo.existsById(id);
    }
}
