package com.impaqgroup.training.logging.service.converter;

import com.impaqgroup.training.logging.model.Attachment;
import com.impaqgroup.training.logging.rest.dto.AttachmentDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoAttachmentConverter implements Converter<AttachmentDto, Attachment> {

    @Override
    public Attachment convert(AttachmentDto attachmentDto) {
        return new Attachment(attachmentDto.getId(), attachmentDto.getName(), attachmentDto.getMimeType(), attachmentDto.getContent());
    }
}
