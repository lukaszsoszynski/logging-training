package com.impaqgroup.training.spring.security.service;

import com.impaqgroup.training.spring.security.model.Post;
import com.impaqgroup.training.spring.security.repository.PostRepository;
import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final ConversionService conversionService;


    @Autowired
    public PostService(PostRepository postRepository, ConversionService conversionService) {
        this.postRepository = postRepository;
        this.conversionService = conversionService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public void create(PostDto postDto) {
        Post post = conversionService.convert(postDto, Post.class);
        post.setId(null);
        post = postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll() {
        return postRepository
                .findAll()
                .stream()
                .map(post -> conversionService.convert(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void remove(Long postId) {
        postRepository.delete(postId);
    }

    @Transactional
    public void update(PostDto postDto) {
        Post post = conversionService.convert(postDto, Post.class);
        postRepository.save(post);
    }
}
