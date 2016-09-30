package com.impaqgroup.training.spring.security.service;

import com.impaqgroup.training.spring.security.model.Post;
import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter implements Converter<Post, PostDto> {

    @Override
    public PostDto convert(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent());
    }
}
