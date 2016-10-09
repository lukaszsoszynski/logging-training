package com.impaqgroup.training.logging.rest;

import com.impaqgroup.training.logging.rest.dto.PostDto;
import com.impaqgroup.training.logging.rest.dto.PostResponseDto;
import com.impaqgroup.training.logging.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    @RequestMapping(value = "/post", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<PostDto> findAll(){
        return postService.findAll();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> findOne(@PathVariable("id") Long id){
        /*@formatter:off*/
        return postService
                .findOne(id)
                .map(postDto -> new ResponseEntity<>(postDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
        /*@formatter:on*/
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createNew(@RequestBody PostDto postDto){
        return postService.create(postDto);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long postId){
        postService.remove(postId);
    }

    @RequestMapping(path = "/post/{id}", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long postId, @RequestBody PostDto postDto){
        postDto.setId(postId);
        postService.update(postDto);
    }
}
