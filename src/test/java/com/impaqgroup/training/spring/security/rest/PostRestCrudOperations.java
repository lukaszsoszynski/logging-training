package com.impaqgroup.training.spring.security.rest;

import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import org.springframework.web.client.RestTemplate;

public class PostRestCrudOperations extends BaseRestCrudOperations<PostDto> {
    public PostRestCrudOperations(RestTemplate restTemplate, RespCrudOperationConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
