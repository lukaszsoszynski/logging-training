package com.impaqgroup.training.logging.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impaqgroup.training.logging.model.Post;
import com.impaqgroup.training.logging.repository.PostRepository;
import com.impaqgroup.training.logging.rest.dto.PostDto;
import com.impaqgroup.training.logging.rest.dto.PostResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

    private final PostRepository postRepository;

    private final ConversionService conversionService;

    @Transactional
    public PostResponseDto create(PostDto postDto) {
        Post post = conversionService.convert(postDto, Post.class);
        post.setId(null);
        post = postRepository.save(post);
        PostResponseDto postResponseDto = conversionService.convert(post, PostResponseDto.class);
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll() {
        /*@formatter:off*/
        return postRepository
                .findAll()
                .stream()
                .map(this::convertModelObjectToDto)
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
        post = postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<PostDto> findOne(Long id) {
        /*@formatter:off*/
        return postRepository
                .findById(id)
                .map(this::convertModelObjectToDto);
        /*@formatter:on*/
    }

    private PostDto convertModelObjectToDto(Post post) {
        return conversionService.convert(post, PostDto.class);
    }
}
