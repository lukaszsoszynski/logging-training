package com.impaqgroup.training.logging.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {

    private Long id;

    private String name;

    private String mimeType;

    private byte[] content;
}
