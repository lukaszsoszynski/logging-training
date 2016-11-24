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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PostService {

//    private final static Logger log = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;

    private final ConversionService conversionService;

    @Transactional
    public PostResponseDto create(PostDto postDto) {
        log.info("Creating post {}", postDto);
        Post post = conversionService.convert(postDto, Post.class);
        post.setId(null);
        post = postRepository.save(post);
        PostResponseDto postResponseDto = conversionService.convert(post, PostResponseDto.class);
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public List<PostDto> findAll() {
        log.info("Searching for all posts");
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
        log.info("Removeing post {}", postId);
        postRepository.delete(postId);
    }

    @Transactional
    public void update(PostDto postDto) {
        log.info("Update post {}", postDto);
        Post post = conversionService.convert(postDto, Post.class);
        post = postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<PostDto> findOne(Long id) {
        log.info("Searching for post {}", id);
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
