package com.impaqgroup.training.spring.security.service.converter;

import com.impaqgroup.training.spring.security.model.Attachment;
import com.impaqgroup.training.spring.security.rest.dto.AttachmentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoAttachmentConverter implements Converter<AttachmentDto, Attachment> {

    @Override
    public Attachment convert(AttachmentDto attachmentDto) {
        return new Attachment(attachmentDto.getId(), attachmentDto.getName(), attachmentDto.getMimeType(), attachmentDto.getContent());
    }
}
