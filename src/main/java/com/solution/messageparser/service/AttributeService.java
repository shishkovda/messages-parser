package com.solution.messageparser.service;

import com.solution.messageparser.entity.Attribute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttributeService {
    Attribute createAttribute(Attribute attribute);
    List<Attribute> getAttributesbyTemplateId(Long templateId);
    Attribute getAttributesbyTemplateIdAndPosition(Long templateId, Integer position);
}
