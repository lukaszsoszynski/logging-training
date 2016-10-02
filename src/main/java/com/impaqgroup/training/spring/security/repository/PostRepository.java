package com.impaqgroup.training.spring.security.repository;

import com.impaqgroup.training.spring.security.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);
}
