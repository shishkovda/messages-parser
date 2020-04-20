package com.solution.messageparser.service;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.entity.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<Value> getValuesByTemplateIdAndAttrId(Long templateId, Long attrId);
}
