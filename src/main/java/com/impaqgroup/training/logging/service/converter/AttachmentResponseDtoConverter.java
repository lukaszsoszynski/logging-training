package com.impaqgroup.training.logging.service.converter;

import com.impaqgroup.training.logging.rest.dto.AttachmentResponseDto;
import com.impaqgroup.training.logging.model.Attachment;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttachmentResponseDtoConverter implements Converter<Attachment, AttachmentResponseDto> {

    @Override
    public AttachmentResponseDto convert(Attachment attachment) {
        return new AttachmentResponseDto(attachment.getId());
    }
}
