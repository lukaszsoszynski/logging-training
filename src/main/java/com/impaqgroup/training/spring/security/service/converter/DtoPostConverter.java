package com.impaqgroup.training.spring.security.service.converter;

import com.impaqgroup.training.spring.security.model.Post;
import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoPostConverter implements Converter<PostDto, Post> {

    @Override
    public Post convert(PostDto postDto) {
        return new Post(postDto.getId(), postDto.getTitle(), postDto.getContent());
    }
}
