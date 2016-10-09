package com.impaqgroup.training.spring.security.rest;

import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.spring.security.rest.dto.PostDto;

public class PostRestCrudOperations extends BaseRestCrudOperations<PostDto> {
    public PostRestCrudOperations(RestTemplate restTemplate, RestCrudOperationConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
