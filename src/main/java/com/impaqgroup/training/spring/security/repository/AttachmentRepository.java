package com.impaqgroup.training.spring.security.repository;

import com.impaqgroup.training.spring.security.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
