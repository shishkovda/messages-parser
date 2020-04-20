package com.solution.messageparser.repository;

import com.solution.messageparser.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
    Template findByRequestor(String requestor);
    Template findById(Long id);
}
