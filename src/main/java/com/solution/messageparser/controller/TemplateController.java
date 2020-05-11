package com.solution.messageparser.controller;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.entity.Template;
import com.solution.messageparser.representation.AttributeRepresentation;
import com.solution.messageparser.representation.TemplateRepresentation;
import com.solution.messageparser.service.AttributeService;
import com.solution.messageparser.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {
    Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    TemplateService templateService;

    @Autowired
    AttributeService attributeService;

    @PostMapping("")
    public ResponseEntity<Long> createTemplate(@RequestBody TemplateRepresentation templateRepresentation) {
        logger.info("createTemplate(): templateRepresentation = " + templateRepresentation);

        Template template = new Template();
        template.setName(templateRepresentation.getName());
        template.setRequestor(templateRepresentation.getRequestor());
        template.setTemplate(templateRepresentation.getTemplate());
        template = templateService.createTemplate(template);

        if(templateRepresentation.getAttributes()!=null){
            for(AttributeRepresentation attributeRepresentation:templateRepresentation.getAttributes()){
                Attribute attribute = new Attribute();
                attribute.setName(attributeRepresentation.getName());
                attribute.setPosition(attributeRepresentation.getPosition());
                attribute.setTemplateId(template.getId());
                attributeService.createAttribute(attribute);
            }
        }

        logger.info("createTemplate(): templateId = " + template.getId());

        return new ResponseEntity<>(template.getId(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplate(@PathVariable("id") String id) {
        logger.info("getTemplate(): id = " + id);

        return new ResponseEntity<>(templateService.getTemplateById(Long.valueOf(id)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public void patchTemplate(@PathVariable("id") String id,
                              @RequestBody TemplateRepresentation templateRepresentation) {
        logger.info("patchTemplate(): id = " + id);
        logger.info("patchTemplate(): templateRepresentation = " + templateRepresentation);

        Template template = templateService.getTemplateById(Long.valueOf(id));

        template.setName(templateRepresentation.getName());
        template.setRequestor(templateRepresentation.getRequestor());
        template.setTemplate(templateRepresentation.getTemplate());

        templateService.updateTemplate(template);
    }

    @GetMapping("/{template_id}/{position}")
    public ResponseEntity<Long> getTemplate(@PathVariable("template_id") String templateId,
                                            @PathVariable("position") String position) {
        logger.info("getTemplate(): templateId = " + templateId);
        logger.info("getTemplate(): position = " + position);

        Attribute attribute = attributeService.
                getAttributesbyTemplateIdAndPosition(Long.valueOf(templateId), Integer.valueOf(position));
        return new ResponseEntity<>(attribute.getId(), HttpStatus.OK);
    }

}
