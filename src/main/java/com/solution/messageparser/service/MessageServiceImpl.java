package com.solution.messageparser.service;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.entity.Value;
import com.solution.messageparser.repository.AttributeRepository;
import com.solution.messageparser.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Autowired
    ValueRepository valueRepository;

    @Override
    public List<Value> getValuesByTemplateIdAndAttrId(Long templateId, Long attrId) {
        return valueRepository.findValueByTemplateIdAndAndAttrId(templateId, attrId);
    }
}
