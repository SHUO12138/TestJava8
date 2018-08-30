package com.test.java8.service;

/**
 * Created by songzhanlong on 2018/8/28.
 */
@FunctionalInterface
public interface SelectUtil<T> {
     boolean process(T t);
}
