package com.impaqgroup.training.logging.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impaqgroup.training.logging.model.Attachment;
import com.impaqgroup.training.logging.repository.AttachmentRepository;
import com.impaqgroup.training.logging.rest.dto.AttachmentDto;
import com.impaqgroup.training.logging.rest.dto.AttachmentResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttachmentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AttachmentService.class);

    private final AttachmentRepository attachmentRepository;

    private final ConversionService conversionService;

    @Transactional
    public AttachmentResponseDto create(AttachmentDto attachmentDto){
        Attachment attachment = conversionService.convert(attachmentDto, Attachment.class);
        attachmentDto.setId(null);
        attachment = attachmentRepository.save(attachment);
        AttachmentResponseDto attachmentResponseDto = conversionService.convert(attachment, AttachmentResponseDto.class);
        LOGGER.info("New attachment created {}", attachmentResponseDto);
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("New attachment created " + attachmentResponseDto);
        }
        return attachmentResponseDto;
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
        LOGGER.info("Attachment removed with id {}", attachmentId);
    }

    @Transactional
    public void update(AttachmentDto attachmentDto){
        Attachment attachment = conversionService.convert(attachmentDto, Attachment.class);
        attachmentRepository.save(attachment);
        LOGGER.info("Attachment updated {}", attachment);
    }

    @Transactional(readOnly = true)
    public Optional<AttachmentDto> findOne(Long id) {
        LOGGER.debug("Searching for attachment by id {}", id);
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
