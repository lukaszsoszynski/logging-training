package com.impaqgroup.training.spring.security.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentResponseDto;
import com.impaqgroup.training.spring.security.service.AttachmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachmentController {

    private final AttachmentService attachmentService;

    @RequestMapping(value = "/attachment", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<AttachmentDto> findAll(){
        return attachmentService.findAll();
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AttachmentDto> findOne(@PathVariable("id") Long id){
        /*@formatter:off*/
        return attachmentService
                .findOne(id)
                .map(attachmentDto -> new ResponseEntity<>(attachmentDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        /*@formatter:on*/
    }

    @RequestMapping(value = "/attachment", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentResponseDto createNew(@RequestBody AttachmentDto attachmentDto){
        return attachmentService.create(attachmentDto);
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long attachmentId){
        attachmentService.remove(attachmentId);
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long attachmentId, @RequestBody AttachmentDto attachmentDto){
        attachmentDto.setId(attachmentId);
        attachmentService.update(attachmentDto);
    }
}
