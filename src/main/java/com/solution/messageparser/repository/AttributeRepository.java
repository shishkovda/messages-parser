package com.solution.messageparser.repository;

import com.solution.messageparser.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
    List<Attribute> findAttributesByTemplateId(Long templateId);
    Attribute findAttributeByTemplateIdAndPosition(Long templateId, Integer position);
}
