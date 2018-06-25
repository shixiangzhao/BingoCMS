package com.shixzh.bcms.framework.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListStreamFilterPeek {

    public static void main(String[] args) {
        List<String> nations = Arrays.asList("A", "B", "C", "A1");
        nations.stream().
                filter(nation -> {
                    return nation.startsWith("A");
                }).
                peek(nation -> System.out.println(nation)).
                map((t) -> {
                    return t + "a";
                }).
                peek(nation -> System.out.println(nation)).
                collect(Collectors.toList());

    }
}
