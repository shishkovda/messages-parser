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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

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
        logger.info("processMessage(): messageRepresentation = " + messageRepresentation);

        String templateId = messageRepresentation.getTemplateId();
        String messageText = messageRepresentation.getMessage();

        Message message = new Message();
        message.setRequestor(templateId);
        message.setMessage(messageText);
        message.setDate(new Date());
//ToDo: save if parsing is ok
        message = messageRepository.save(message);
        logger.info("processMessage(): message = " + message);

        Template template = templateService.getTemplateById(Long.valueOf(templateId));
        logger.info("processMessage(): template = " + template);

        List<Attribute> attributes = attributeService.getAttributesbyTemplateId(Long.valueOf(templateId));
        logger.info("processMessage(): attributes = " + attributes);

        List<String> values = parser.parse(template.getTemplate(), messageText);
        logger.info("processMessage(): values = " + values);

//ToDo: check
        //        template.setAttributes(attributes);

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
    public List<String> getValues(@RequestParam("templateId") String templateId,
                                  @RequestParam("attrId") String attrId){
        logger.info("getValues(): templateId = " + templateId);
        logger.info("getValues(): attrId = " + attrId);

        List<Value> values = valueRepository.
                findValueByTemplateIdAndAndAttrId(Long.valueOf(templateId), Long.valueOf(attrId));
        List<String> result = new ArrayList<>();
//ToDo: check names
        for(Value value:values){
            result.add(value.getValue());
        }
        return result;
    }

}
