package com.impaqgroup.training.spring.security.rest;


import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.spring.security.rest.dto.PostDto;
import com.impaqgroup.training.spring.security.rest.dto.PostResponseDto;

public class PostControllerIT {

    private final static AtomicLong ATOMIC_LONG_COUNTER = new AtomicLong(0);
    public static final int THREAD_COUNT = 5;

    private PostRestCrudOperations postRestCrudOperations;

    @Before
    public void setUp() {
        /*@formatter:off*/
        RestCrudOperationConfiguration configuration = RestCrudOperationConfiguration
                .builder()
                .rootResourcePath("http://localhost:8080/post")
                .username("ala")
                .password("makota")
                .build();
        /*@formatter:on*/
        this.postRestCrudOperations = new PostRestCrudOperations(new RestTemplate(), configuration);
    }

    @Test
    public void shouldCreatePost() {
        //when
        PostResponseDto postResponseDto = postRestCrudOperations.create(createPost(), PostResponseDto.class);

        //when
        assertThat(postResponseDto).isNotNull();
        assertThat(postResponseDto.getId()).isPositive();
    }

    @Test
    public void shouldFindPost(){
        //given
        PostResponseDto postResponseDto = postRestCrudOperations.create(createPost(), PostResponseDto.class);

        //when
        PostDto post = postRestCrudOperations.find(postResponseDto.getId(), PostDto.class);

        //then
        assertThat(post).isNotNull();
    }

    @Test
    public void shouldUpdatePost(){
        //given
        PostResponseDto postResponseDto = postRestCrudOperations.create(createPost(), PostResponseDto.class);
        PostDto post = postRestCrudOperations.find(postResponseDto.getId(), PostDto.class);
        post.setContent(post.getContent() + "-" + ATOMIC_LONG_COUNTER.getAndIncrement());

        //when
        HttpStatus status = postRestCrudOperations.update(post.getId(), post);

        //then
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void stressTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for(int i = 0; i < THREAD_COUNT; ++i){
            executorService.submit(createInfinitiCrudTask());
        }
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    private Runnable createInfinitiCrudTask(){
        return () -> {
            while (true) {
                executeFullCrud();
            }
        };
    }

    private void executeFullCrud(){
        PostResponseDto postResponseDto = postRestCrudOperations.create(createPost(), PostResponseDto.class);
        PostDto post = postRestCrudOperations.find(postResponseDto.getId(), PostDto.class);
        post.setContent(post.getContent() + "-" + ATOMIC_LONG_COUNTER.getAndIncrement());
        postRestCrudOperations.update(post.getId(), post);
        postRestCrudOperations.remove(post.getId());
    }

    private PostDto createPost() {
        long number = ATOMIC_LONG_COUNTER.getAndIncrement();
        return new PostDto(null, "title-" + number, "content-" + number);
    }

}