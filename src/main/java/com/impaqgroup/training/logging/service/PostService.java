package com.impaqgroup.training.logging.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;

    private final ConversionService conversionService;

    @Transactional
    public PostResponseDto create(PostDto postDto) {
        Post post = conversionService.convert(postDto, Post.class);
        post.setId(null);
        post = postRepository.save(post);
        PostResponseDto postResponseDto = conversionService.convert(post, PostResponseDto.class);
        LOGGER.info("New post with id {} added", postResponseDto);
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll() {
        LOGGER.debug("Searching for all post");
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
        LOGGER.info("Post updated {}", post);
    }

    @Transactional(readOnly = true)
    public Optional<PostDto> findOne(Long id) {
        LOGGER.debug("Searching for post by id {}", id);
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
