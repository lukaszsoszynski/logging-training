package com.impaqgroup.training.spring.security.rest;

import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import com.impaqgroup.training.spring.security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> findAll(){
        return postService.findAll();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody PostDto postDto){
        postService.create(postDto);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long postId){
        postService.remove(postId);
    }

    @RequestMapping(path = "/post/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long postId, @RequestBody PostDto postDto){
        postDto.setId(postId);
        postService.update(postDto);
    }
}
