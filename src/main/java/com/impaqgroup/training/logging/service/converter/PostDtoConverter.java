package com.impaqgroup.training.logging.service.converter;

import com.impaqgroup.training.logging.model.Post;
import com.impaqgroup.training.logging.rest.dto.PostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter implements Converter<Post, PostDto> {

    @Override
    public PostDto convert(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent());
    }
}
