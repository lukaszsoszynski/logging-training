package com.impaqgroup.training.logging.service.converter;

import com.impaqgroup.training.logging.model.Attachment;
import com.impaqgroup.training.logging.rest.dto.AttachmentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttachmentDtoConverter implements Converter<Attachment, AttachmentDto> {
    @Override
    public AttachmentDto convert(Attachment attachment) {
        return new AttachmentDto(attachment.getId(), attachment.getName(), attachment.getMimeType(), attachment.getContent());
    }
}
