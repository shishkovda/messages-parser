package com.solution.messageparser.service;

import com.solution.messageparser.entity.Template;
import org.springframework.stereotype.Service;

@Service
public interface TemplateService {
    Template createTemplate(Template template);
    Template getTemplateByRequestor(String requestor);
    Template getTemplateById(Long id);
}
