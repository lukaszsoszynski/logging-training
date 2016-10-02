package com.impaqgroup.training.spring.security.service;

import com.impaqgroup.training.spring.security.model.Attachment;
import com.impaqgroup.training.spring.security.repository.AttachmentRepository;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final ConversionService conversionService;

    @Transactional
    public AttachmentResponseDto create(AttachmentDto attachmentDto){
        Attachment attachment = conversionService.convert(attachmentDto, Attachment.class);
        attachmentDto.setId(null);
        attachment = attachmentRepository.save(attachment);
        return conversionService.convert(attachment, AttachmentResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<AttachmentDto> findAll(){
        /*@formatter:off*/
        return attachmentRepository
                .findAll()
                .stream()
                .map(this::convertModelObjectToDto)
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

    @Transactional(readOnly = true)
    public Optional<AttachmentDto> findOne(Long id) {
        /*@formatter:off*/
        return attachmentRepository
                .findById(id)
                .map(this::convertModelObjectToDto);
        /*@formatter:on*/
    }

    private AttachmentDto convertModelObjectToDto(Attachment attachment) {
        return conversionService.convert(attachment, AttachmentDto.class);
    }
}
