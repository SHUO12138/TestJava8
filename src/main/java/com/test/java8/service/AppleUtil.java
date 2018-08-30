package com.test.java8.service;

import com.test.java8.domain.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by songzhanlong on 2018/8/29.
 */
public class AppleUtil {
    /**
     * 使用JDK8内置函数式接口
     * @param apples
     * @param predicate
     * @return
     */
    public static List<Apple> getApples(List<Apple> apples, Predicate<Apple> predicate){
        List ls = new ArrayList();
        for (Apple a:apples){
            if (predicate.test(a))
                ls.add(a);
        }
        return ls;
    }
    /**
     * 使用J自定义的函数式接口
     * @param apples
     * @param selectUtil
     * @return
     */
    public static List<Apple> getApplesDef(List<Apple> apples, SelectUtil<Apple> selectUtil){
        List ls = new ArrayList();
        for (Apple a:apples){
            if (selectUtil.process(a))
                ls.add(a);
        }
        return ls;
    }

    /**
     * 传统处理集合数据
     * @param appleList
     */
    public static void showApples(List<Apple> appleList) {
        for (Apple apple:appleList)
            System.out.println(apple.toString());
    }
}
