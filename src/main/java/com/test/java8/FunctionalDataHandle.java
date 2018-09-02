package com.test.java8;

import com.test.java8.domain.Apple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 函数式数据处理：流
 */
public class FunctionalDataHandle {
    static List<Apple> appleList = new ArrayList();

    static {
        Apple tempApple = null;
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                tempApple = new Apple("green", i);
            } else {
                tempApple = new Apple("red", i);
            }
            appleList.add(tempApple);
        }
        appleList.addAll(Arrays.asList(new Apple("green", 10), new Apple("green", 11)));
    }

    public static void main(String[] args) {
        List<String> apples = appleList.stream().filter(apple -> apple.getColor().equals("red")).map(Apple::toString).collect(Collectors.toList());
        apples.forEach(s -> System.out.println(s));
//        筛选和切片
        apples = appleList.stream().filter(apple -> apple.getColor().equals("red")).skip(1).limit(2).map(Apple::toString).collect(Collectors.toList());
//        流的扁平化
        List<String> words = Arrays.asList("hello", "world");
        List<String> uniqueCharacters = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        uniqueCharacters.stream().forEach(s -> System.out.println(s));
//        查找和匹配
        if (words.stream().anyMatch((word) -> word.equals("hello")))
            System.out.println("words has word:hello");
        if (words.stream().allMatch(word -> word.contains("o")))
            System.out.println("words has charactor:o");
        if (words.stream().noneMatch(word -> word.contains("a")))
            System.out.println("words has not charactor:a");
//        归约
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numberList.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum:" + sum);
        int product = numberList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("product:" + product);
        Optional<Integer> max = numberList.stream().reduce(Integer::max);
//        map-reduce模式
        int count = appleList.stream().map(apple -> 1).reduce(0, (a, b) -> a + b);
//        映射到数值流
        IntStream intStream = numberList.stream().mapToInt(number -> number);
        Stream<Integer> stream = intStream.boxed();
//        默认值OptionalInt
        OptionalInt maxCalories = numberList.stream().mapToInt(a -> a).max();
        maxCalories.orElse(1);
//        数值范围
        IntStream evenNumbers = IntStream.range(1, 5).filter(a -> a > 3);
        IntStream evenNumbers2 = IntStream.rangeClosed(1, 5).filter(a -> a > 3);
//勾股数
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed().
                flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
//        由值创建流
        Stream stream1=Stream.of("ab","df","wee");
//        由数组创建流
        int[] nums = {1,2,3,4};
        IntStream numsS = Arrays.stream(nums);
//        由文件生成流
        try {
            Stream<String> fileStream = Files.lines(Paths.get("/songzhanlong/hello"));
        }catch (IOException e){
            e.printStackTrace();
        }
//        由函数生成流:创建无限流
        Stream.iterate(0,a->a+2);
        Stream.generate(Math::random).limit(5);
    }
}
