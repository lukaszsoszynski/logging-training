package com.impaqgroup.training.logging.rest;

import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.logging.rest.dto.PostDto;

public class PostRestCrudOperations extends BaseRestCrudOperations<PostDto> {
    public PostRestCrudOperations(RestTemplate restTemplate, RestCrudOperationConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
