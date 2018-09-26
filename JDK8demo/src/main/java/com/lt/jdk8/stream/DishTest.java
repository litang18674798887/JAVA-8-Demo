package com.lt.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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


    @Test
    public void testMap(){

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> collect = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void testLimit(){
        //limit 方法用于获取指定数量的流
        // 使用 limit 方法打印出 10 条数据：
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

    }

    /**
     * sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
     */
    @Test
    public void testSorted(){
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**
     * parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
     */
    @Test
    public void testParallel(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        int count = (int) strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    @Test
    public void testCollectors(){
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    @Test
    public void testCount(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

}
