package com.impaqgroup.training.spring.security.rest;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

import static java.util.Collections.emptyMap;

@RequiredArgsConstructor
public abstract class BaseRestCrudOperations<Dto> {

    private final RestTemplate restTemplate;

    private final RespCrudOperationConfiguration configuration;

    public <ResponseDto> ResponseDto create(Dto dto, Class<ResponseDto> clazz) {
        HttpEntity<Dto> httpEntity = new HttpEntity<>(dto, createHeaderMapWithBasicAuth());
        return restTemplate.exchange(configuration.getRootResourcePath(), HttpMethod.POST, httpEntity, clazz, emptyMap()).getBody();
    }

    public <T> Dto find(T id, Class<Dto> clazz){
        return restTemplate.exchange(createPathWithId(), HttpMethod.GET, createHttpEntityWithEmptyBody(), clazz, id).getBody();
    }

    public HttpStatus update(Long id, Dto dto){
        HttpEntity<Dto> httpEntity = new HttpEntity<>(dto, createHeaderMapWithBasicAuth());
        return restTemplate.exchange(createPathWithId(), HttpMethod.PUT, httpEntity, Void.class, id).getStatusCode();
    }

    public HttpStatus remove(Long id){
        return restTemplate.exchange(createPathWithId(), HttpMethod.DELETE, createHttpEntityWithEmptyBody(), Void.class, id).getStatusCode();
    }

    private MultiValueMap<String, String> createHeaderMapWithBasicAuth() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, Collections.singletonList(getBasicAuthHeaderValue()));
        return headers;
    }

    private String getBasicAuthHeaderValue() {
        String auth = configuration.getUsername() + ":" + configuration.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(encodedAuth);
    }

    private String createPathWithId() {
        return configuration.getRootResourcePath() + "/{id}";
    }

    private HttpEntity<Void> createHttpEntityWithEmptyBody() {
        return new HttpEntity<>(null, createHeaderMapWithBasicAuth());
    }
}
