package com.lt.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author : litang
 * @date : Create in 2018/9/26
 */
public class DishTest {

    @Test
    public void getLowCaloricDishesNames(){

        List<Dish> dishList = Dish.menu;
        List<String> collect = dishList.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishList);


        System.out.println("---------筛选出素食---------");
        List<Dish> vegetarianMenu =
                dishList.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        vegetarianMenu.forEach(System.out::println);





    }

    /**
     * 筛选出偶数，再去除重复的
     */
    @Test
    public void test01(){
        System.out.println("---------去重 --------");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * 筛选出热量超过300的前3道菜，
     */
    @Test
    public void test02(){
        List<Dish> dishList = Dish.menu;
        dishList.stream()
                .filter(dish -> dish.getCalories() > 800)
                .limit(3)
                .collect(toList());
        dishList.forEach(System.out::println);

    }




}
