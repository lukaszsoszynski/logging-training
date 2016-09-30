package com.impaqgroup.training.spring.security.service.converter;

import com.impaqgroup.training.spring.security.model.Attachment;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttachmentDtoConverter implements Converter<Attachment, AttachmentDto> {
    @Override
    public AttachmentDto convert(Attachment attachment) {
        return new AttachmentDto(attachment.getId(), attachment.getName(), attachment.getMimeType(), attachment.getContent());
    }
}
