package com.solution.messageparser.repository;

import com.solution.messageparser.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ValueRepository extends JpaRepository<Value, UUID> {
    List<Value> findValueByTemplateIdAndAndAttrId(Long templateId, Long attr_id);
}
