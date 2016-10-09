package com.impaqgroup.training.logging.service.converter;

import com.impaqgroup.training.logging.model.Post;
import com.impaqgroup.training.logging.rest.dto.PostResponseDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostResponseDtoConverter implements Converter<Post, PostResponseDto> {
    @Override
    public PostResponseDto convert(Post post) {
        return new PostResponseDto(post.getId());
    }
}
