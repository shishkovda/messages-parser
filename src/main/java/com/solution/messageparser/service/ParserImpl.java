package com.solution.messageparser.service;

import com.solution.messageparser.controller.MessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserImpl implements Parser {
    Logger logger = LoggerFactory.getLogger(ParserImpl.class);

    @Override
    public List<String> parse(String template, String message) {
        logger.info("parse(): template = " + template);
        logger.info("parse(): message = " + message);
        message = message.replaceAll("\"", "");

        Pattern pattern = Pattern.compile("\\{attribute}");
        Matcher matcher = pattern.matcher(template);

        List<Integer> attributeIndexes = new ArrayList<>();

        while(matcher.find()){
            attributeIndexes.add(matcher.start());
        }

        List<String> templateWords = new ArrayList<>();
        int prevIndex = 0;
        for(Integer i:attributeIndexes){
            templateWords.add(template.substring(prevIndex, i));
            prevIndex = i+11;
        }
        templateWords.add(template.substring(prevIndex));

        List<String> attributeValues = new ArrayList<>();
        int idx1 = 0;
        for(String templateWord:templateWords){
            int idx2 = message.indexOf(templateWord, idx1);
            String substr = null;
            if("".equals(templateWord)){
                substr = message.substring(idx1);
            } else {
                substr = message.substring(idx1, idx2);
            }
            if(!substr.equals("")) {
                attributeValues.add(substr);
            }
            idx1 = idx2+templateWord.length();
        }

        return attributeValues;
    }
}
