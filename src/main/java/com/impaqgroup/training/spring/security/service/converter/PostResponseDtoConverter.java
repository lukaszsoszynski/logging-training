package com.impaqgroup.training.spring.security.service.converter;

import com.impaqgroup.training.spring.security.model.Post;
import com.impaqgroup.training.spring.security.rest.dto.PostResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostResponseDtoConverter implements Converter<Post, PostResponseDto> {
    @Override
    public PostResponseDto convert(Post post) {
        return new PostResponseDto(post.getId());
    }
}
