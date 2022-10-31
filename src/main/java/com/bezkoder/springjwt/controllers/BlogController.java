package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Blog;
import com.bezkoder.springjwt.payload.ResponseWithMessage;
import com.bezkoder.springjwt.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;


    @GetMapping
    public ResponseEntity<ResponseWithMessage<List<Blog>>> getAll(){
        List<Blog> blogs;
        try {
            blogs = blogService.findAll();
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Posts repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseWithMessage<>(blogs, null), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseWithMessage<Optional<Blog>>> getById(@PathVariable Long id){
        Optional<Blog> result;
        try {
            result = blogService.findById(id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseWithMessage<>(result, null), HttpStatus.OK);
    }

    @GetMapping(params = "posterName")
    public ResponseEntity<ResponseWithMessage<List<Blog>>> getByPosterName(@RequestParam String posterName){
        // TODO: Change this request to take the username from the cookie (case: corresponding user) or user has role admin
        List<Blog> results;
        try {
            results = blogService.findBlogByPosterName(posterName);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Post repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ResponseWithMessage<>(results, null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseWithMessage<Blog>> postBlog(@CookieValue(name="bezkoder") String cookie, @RequestBody Blog blog){
        try {
            Blog newBlog = blogService.createBlog(blog);
            return new ResponseEntity<>(new ResponseWithMessage<>(newBlog, "Blogpost successfully created"), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blogs repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path="{id}/{b}")
    public ResponseEntity<ResponseWithMessage<Blog>> editBlog(@PathVariable Long id, @RequestBody Blog b){
        // TODO: Authorize the user who created the answer is the same as this one requesting this action & exists in the database
        try {
            if(blogService.exists(id)) {
                Blog updatedBlog = blogService.updateBlog(id, b);
                return new ResponseEntity<>(new ResponseWithMessage<>(updatedBlog, "Blog successfully updated"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog not found"), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<ResponseWithMessage<Blog>> deleteBlog(@PathVariable Long id){
        // TODO: Authorize the user who created the answer is the same as this one requesting this action & exists in the database
        try {
            if(blogService.exists(id)) {
                blogService.deleteBlog(id);
                return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog successfully deleted"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog not found"), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Blog repository not responding"), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseWithMessage<>(null, "Something went wrong..."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}