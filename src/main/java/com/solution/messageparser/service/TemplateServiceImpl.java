package com.solution.messageparser.service;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.entity.Template;
import com.solution.messageparser.repository.AttributeRepository;
import com.solution.messageparser.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public void updateTemplate(Template template) {
        templateRepository.save(template);
    }

    @Override
    public Template getTemplateByRequestor(String requestor) {
        Template template = templateRepository.findByRequestor(requestor);
        List<Attribute> attributes = attributeRepository.findAttributesByTemplateId(template.getId());
        template.setAttributes(attributes);

        return template;
    }

    @Override
    public Template getTemplateById(Long id) {
        return templateRepository.findById(id);
    }
}
