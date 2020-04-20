package com.solution.messageparser.controller;

import com.solution.messageparser.entity.Attribute;
import com.solution.messageparser.entity.Message;
import com.solution.messageparser.entity.Template;
import com.solution.messageparser.entity.Value;
import com.solution.messageparser.repository.MessageRepository;
import com.solution.messageparser.repository.ValueRepository;
import com.solution.messageparser.representation.MessageRepresentation;
import com.solution.messageparser.service.AttributeService;
import com.solution.messageparser.service.Parser;
import com.solution.messageparser.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    TemplateService templateService;

    @Autowired
    ValueRepository valueRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AttributeService attributeService;

    @Autowired
    Parser parser;

    @PostMapping("")
    public void processMessage(@RequestBody MessageRepresentation messageRepresentation){
        String templateId = messageRepresentation.getTemplateId();
        String messageText = messageRepresentation.getMessage();

        Message message = new Message();
        message.setRequestor(templateId);
        message.setMessage(messageText);
        message.setDate(new Date());
        message = messageRepository.save(message);

        Template template = templateService.getTemplateById(Long.valueOf(templateId));
        List<Attribute> attributes = attributeService.getAttributesbyTemplateId(Long.valueOf(templateId));
        List<String> values = parser.parse(template.getTemplate(), messageText);
        template.setAttributes(attributes);

        Collections.sort(attributes, new Comparator<Attribute>() {
            @Override
            public int compare(Attribute o1, Attribute o2) {
                return o1.getPosition()-o2.getPosition();
            }
        });

        for(int i = 0; i<attributes.size(); i++){
            Value value = new Value();
            value.setTemplateId(template.getId());
            value.setAttrId(attributes.get(i).getId());
            value.setValue(values.get(i));
            value.setMessageId(message.getId());
            valueRepository.save(value);
        }

    }

    @GetMapping("")
    public List<Value> getValues(@RequestParam("templateId") String templateId, @RequestParam("attrId") String attrId){
        return valueRepository.findValueByTemplateIdAndAndAttrId(Long.valueOf(templateId), Long.valueOf(attrId));
    }

}
