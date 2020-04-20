package com.solution.messageparser.service;

import java.util.List;

public interface Parser {
    public List<String> parse(String template, String message);
}
