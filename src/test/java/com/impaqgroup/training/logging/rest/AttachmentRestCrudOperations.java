package com.impaqgroup.training.logging.rest;

import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.logging.rest.dto.AttachmentDto;

public class AttachmentRestCrudOperations extends BaseRestCrudOperations<AttachmentDto>{

    public AttachmentRestCrudOperations(RestTemplate restTemplate, RestCrudOperationConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
