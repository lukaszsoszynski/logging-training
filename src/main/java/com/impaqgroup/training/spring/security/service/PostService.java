package com.impaqgroup.training.spring.security.service;

import com.impaqgroup.training.spring.security.model.Post;
import com.impaqgroup.training.spring.security.repository.PostRepository;
import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

    private final PostRepository postRepository;

    private final ConversionService conversionService;

    @Transactional
    public void create(PostDto postDto) {
        Post post = conversionService.convert(postDto, Post.class);
        post.setId(null);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll() {
        /*@formatter:off*/
        return postRepository
                .findAll()
                .stream()
                .map(post -> conversionService.convert(post, PostDto.class))
                .collect(Collectors.toList());
        /*@formatter:on*/
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
