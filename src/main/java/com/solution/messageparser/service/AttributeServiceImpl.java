package com.solution.messageparser.service;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Attribute createAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public List<Attribute> getAttributesbyTemplateId(Long templateId) {
        return attributeRepository.findAttributesByTemplateId(templateId);
    }

    @Override
    public Attribute getAttributesbyTemplateIdAndPosition(Long templateId, Integer position) {
        return attributeRepository.findAttributeByTemplateIdAndPosition(templateId, position);
    }
}
