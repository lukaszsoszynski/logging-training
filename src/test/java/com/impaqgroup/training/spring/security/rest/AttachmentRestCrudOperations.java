package com.impaqgroup.training.spring.security.rest;

import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;

public class AttachmentRestCrudOperations extends BaseRestCrudOperations<AttachmentDto>{

    public AttachmentRestCrudOperations(RestTemplate restTemplate, RestCrudOperationConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
