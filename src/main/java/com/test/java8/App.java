package com.test.java8;

import com.test.java8.domain.Apple;
import com.test.java8.service.AppleUtil;
import com.test.java8.service.SelectUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * Created by songzhanlong on 2018/8/28.
 */
public class App {

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
        appleList.addAll(Arrays.asList(new Apple("green",10),new Apple("green",11))) ;
    }

    public static void main(String[] args) {
//        通过匿名类实现自定义过滤条件
        System.out.println("通过匿名类实现自定义过滤条件-----自定义接口");
        List<Apple> greenAppleList = AppleUtil.getApplesDef(appleList, new SelectUtil<Apple>() {
            @Override
            public boolean process(Apple apple) {
                if (apple.getColor().equals("green"))
                    return true;
                else
                    return false;
            }
        });
        List<Apple> redAppleList = AppleUtil.getApplesDef(appleList, new SelectUtil<Apple>() {
            @Override
            public boolean process(Apple apple) {
                if (apple.getColor().equals("red"))
                    return true;
                else
                    return false;
            }
        });
        System.out.println("========青苹果-----传统循环输出===========");
        AppleUtil.showApples(greenAppleList);
        System.out.println("========红苹果-----传统循环输出===========");
        AppleUtil.showApples(redAppleList);

        System.out.println("========lambda表达式实现自定义过滤条件-----自定义接口===========");
        List<Apple> weightApples = AppleUtil.getApplesDef(appleList, (apple) -> apple.getWeight() > 7);
        System.out.println("========大苹果（weight>7）-----forEach循环输出===========");
        weightApples.forEach((apple) -> System.out.println(apple.toString()));
        System.out.println("========使用内置函数式接口----Predicate===========");
        greenAppleList = AppleUtil.getApples(appleList, (apple) -> apple.getColor().equals("green"));
        greenAppleList.forEach(apple -> System.out.println(apple.toString()));
        System.out.println("========青苹果改为黄苹果===========");
        greenAppleList.forEach(a -> {
            a.setColor("yellow");
            System.out.println(a.toString());
        });
        //链式编程：过滤出红苹果+变为黑苹果+展示所有黑苹果
        System.out.println("========链式编程：过滤出红苹果+变为黑苹果+展示所有黑苹果===========");
        AppleUtil.getApples(appleList, apple -> apple.getColor().equals("red")).forEach(apple -> {
            apple.setColor("black");
            System.out.println(apple.toString());
        });

        System.out.println("========lambda 使用局部变量===========");
        int weight = 50;
        AppleUtil.getApples(appleList,apple -> apple.getWeight()==5).forEach(apple -> {
            apple.setWeight(weight);
            System.out.println(apple.toString());
        });
//        Error:(79, 29) java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
//        weight=60;

        System.out.println("========lambda 方法引用===========");
        List<Apple> sortApples = Arrays.asList(new Apple("red",2),new Apple("blue",1),new Apple("yellow",4));
        sortApples.sort(comparing(Apple::getWeight));
        sortApples.forEach(apple -> System.out.println(apple.toString()));
        Function<String,Integer> stringtoInteger = s -> Integer.parseInt(s);
        stringtoInteger = Integer::parseInt;
        Supplier<Apple> appleSupplier = Apple::new;
        Apple apple1 =  appleSupplier.get();
        BiFunction<String,Integer,Apple> appleBiFunction = Apple::new;
        apple1 = appleBiFunction.apply("red",3);

    }
}
