package com.impaqgroup.training.spring.security.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.*;
import org.springframework.web.client.RestTemplate;

import com.impaqgroup.training.spring.security.rest.dto.*;

public class AttachmentControllerTest {

    private final static AtomicLong ATOMIC_LONG_COUNTER = new AtomicLong(0);
    public static final int THREAD_COUNT = 5;

    private AttachmentRestCrudOperations attachmentRestCrudOperations;

    @Before
    public void setUp() throws Exception {
        /*@formatter:off*/
        RestCrudOperationConfiguration configuration = RestCrudOperationConfiguration
                .builder()
                .rootResourcePath("http://localhost:8080/attachment")
                .username("bartek")
                .password("mapsa")
                .build();
        /*@formatter:on*/
        this.attachmentRestCrudOperations = new AttachmentRestCrudOperations(new RestTemplate(), configuration);
    }

    @Test
    public void shouldCreateAttachment(){
        //when
        AttachmentResponseDto attachmentResponseDto = attachmentRestCrudOperations.create(createAttachment(), AttachmentResponseDto.class);

        //then
        assertThat(attachmentResponseDto).isNotNull();
        assertThat(attachmentResponseDto.getId()).isPositive();
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
        AttachmentResponseDto postResponseDto = attachmentRestCrudOperations.create(createAttachment(), AttachmentResponseDto.class);
        AttachmentDto post = attachmentRestCrudOperations.find(postResponseDto.getId(), AttachmentDto.class);
        post.setName(post.getName() + "-" + ATOMIC_LONG_COUNTER.getAndIncrement());
        attachmentRestCrudOperations.update(post.getId(), post);
        attachmentRestCrudOperations.remove(post.getId());
    }

    private AttachmentDto createAttachment() {
        long number = ATOMIC_LONG_COUNTER.getAndIncrement();
        String content = "content " + number;
        return new AttachmentDto(null, "name-" + number, "application/octet-stream", null);
    }

}