package com.impaqgroup.training.spring.security.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestCrudOperationConfiguration {

    private final String rootResourcePath;
    private final String username;
    private final String password;
}
