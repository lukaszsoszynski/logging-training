package com.impaqgroup.training.spring.security.service;

import com.impaqgroup.training.spring.security.model.Attachment;
import com.impaqgroup.training.spring.security.repository.AttachmentRepository;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final ConversionService conversionService;

    @Transactional
    public void create(AttachmentDto attachmentDto){
        Attachment attachment = conversionService.convert(attachmentDto, Attachment.class);
        attachmentDto.setId(null);
        attachmentRepository.save(attachment);
    }

    @Transactional(readOnly = true)
    public List<AttachmentDto> findAll(){
        /*@formatter:off*/
        return attachmentRepository
                .findAll()
                .stream()
                .map(attachment -> conversionService.convert(attachment, AttachmentDto.class))
                .collect(Collectors.toList());
        /*@formatter:on*/
    }

    @Transactional
    public void remove(Long attachmentId){
        attachmentRepository.delete(attachmentId);
    }

    @Transactional
    public void update(AttachmentDto attachmentDto){
        Attachment attachment = conversionService.convert(attachmentDto, Attachment.class);
        attachmentRepository.save(attachment);
    }
}
