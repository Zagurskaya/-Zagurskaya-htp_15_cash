package com.zagurskaya.cash.controller.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseDataParser<T> {


    T parse(String text);

    default List<String> parsTextToRowList(String text, String separator) {

        List<String> elementList = Arrays.stream(text
                .split(separator))
                .filter(s -> s.length() != 0)
                .collect(Collectors.toList());
        return elementList;
    }
}
