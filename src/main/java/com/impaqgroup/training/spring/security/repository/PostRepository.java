package com.impaqgroup.training.spring.security.repository;

import com.impaqgroup.training.spring.security.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
