package com.impaqgroup.training.logging.repository;

import com.impaqgroup.training.logging.model.Attachment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Optional<Attachment> findById(Long id);
}
