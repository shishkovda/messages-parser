package com.solution.messageparser.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserImplTest {

    Parser parser = new ParserImpl();

    @Test
    void parse1() {
        String template = "Перевод. Карта *8753 300 RUB. На ДР Олега {attribute}";
        String message = "Перевод. Карта *8753 300 RUB. На ДР Олега Краснов";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Краснов");
        List<String> actualResult = parser.parse(template, message);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse2() {
        String template = "qwerty {attribute} qwerty2 {attribute} qwerty3";
        String message = "qwerty 12345 qwerty2 1234567890 qwerty3";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("12345");
        expectedResult.add("1234567890");
        List<String> actualResult = parser.parse(template, message);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parse3() {
        String template = "cnojdncldnl {attribute} {attribute}";
        String message = "cnojdncldnl Krasnov 3";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Krasnov");
        expectedResult.add("3");
        List<String> actualResult = parser.parse(template, message);

        assertEquals(expectedResult, actualResult);
    }
}