package com.impaqgroup.training.spring.security.rest;

import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import com.impaqgroup.training.spring.security.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachmentController {

    private final AttachmentService attachmentService;

    @RequestMapping(value = "/attachment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AttachmentDto> findAll(){
        return attachmentService.findAll();
    }

    @RequestMapping(value = "/attachment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public void createNew(@RequestBody AttachmentDto attachmentDto){
        attachmentService.create(attachmentDto);
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long attachmentId){
        attachmentService.remove(attachmentId);
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long attachmentId, @RequestBody AttachmentDto attachmentDto){
        attachmentDto.setId(attachmentId);
        attachmentService.update(attachmentDto);
    }
}
