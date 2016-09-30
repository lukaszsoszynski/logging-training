package com.impaqgroup.training.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {

    private final static String ATTACHMENT_SEQUENCE_GENERATOR = "attachment_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ATTACHMENT_SEQUENCE_GENERATOR)
    @SequenceGenerator(name = ATTACHMENT_SEQUENCE_GENERATOR, sequenceName = "seq_attachment", allocationSize = 1, initialValue = 1000)
    private Long id;

    private String name;

    private String mimeType;

    private byte[] content;
}
