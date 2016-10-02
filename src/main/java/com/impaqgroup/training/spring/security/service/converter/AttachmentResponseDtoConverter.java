package com.impaqgroup.training.spring.security.service.converter;

import com.impaqgroup.training.spring.security.model.Attachment;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttachmentResponseDtoConverter implements Converter<Attachment, AttachmentResponseDto> {

    @Override
    public AttachmentResponseDto convert(Attachment attachment) {
        return new AttachmentResponseDto(attachment.getId());
    }
}
