package com.lt.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。
 * 尤其是对于数据从业人员来说，对数据做各种操作转换是再正常不过的需求，基本每天都会用到。
 *
 * @author : litang
 * @date : Create in 2018/9/26
 */
public class StreamTest {

    /**
     * 求一个集合中字符串长度小于5的数量。
     */
    @Test
    public  void test01(){
        List<String> list = Arrays.asList("java", "scala", "python", "shell", "ruby");
        int num = 0;
        for(String lan: list) {
            if(lan.length() < 5) {
                num++;
            }
        }
        System.out.println(num);
        System.out.println("JAVA8  Stream ");
        long count = list.parallelStream().filter(x -> x.length() < 5).count();
        System.out.println(count);
    }

    /**
     * 什么是Stream
     *
     * Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。原始版本的 Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作；高级版本
     * 的 Stream，用户只要给出需要对其包含的元素执行什么操作，比如 “过滤掉长度大于 10 的字符串”、“获取每个字符串的首字母”等，Stream 会隐式地在内部进行遍历，做出相应的数据转换。
     * Stream 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。
     * 而和迭代器又不同的是，Stream 可以并行化操作，迭代器只能命令式地、串行化操作。顾名思义，当使用串行方式去遍历时，每个 item 读完后再读下一个 item。而使用并行去遍历时，数据会被分成多个段
     * ，其中每一个都在不同的线程中处理，然后将结果一起输出。Stream 的并行操作依赖于 Java7 中引入的 Fork/Join 框架（JSR166y）来拆分任务和加速处理过程。
     */

    /**
     * Stream和Collection的区别主要有
     * 1.stream本身并不存储数据，数据是存储在对应的collection里，或者在需要的时候才生成的；
     * 2.stream不会修改数据源，总是返回新的stream；
     * 3.stream的操作是懒执行(lazy)的：仅当最终的结果需要的时候才会执行，比如上面的例子中，结果仅需要前3个长度大于7的字符串，那么在找到前3个长度符合要求的字符串后，filter()将停止执行；
     *
     * 使用stream的步骤如下：
     * 1.创建stream；
     * 2.通过一个或多个中间操作(intermediate operations)将初始stream转换为另一个stream；
     * 3.通过中止操作(terminal operation)获取结果；该操作触发之前的懒操作的执行，中止操作后，该stream关闭，不能再使用了；
     *
     */

    @Test
    public void numberStreamConstruct(){

        IntStream.of(new int[]{1,2,3}).forEach(System.out::println);
        System.out.println("---------------------");
        IntStream.range(1,3).forEach(System.out::println);
        System.out.println("---------------------");
        IntStream.rangeClosed(1,3).forEach(System.out::println);
    }

    @Test
    public void Stream(){

        //1.初始化一个流
        Stream<String> stream = Stream.of("a", "b", "c");

        //2.数据转换一个流
        String[] strArray=new String[]{"a","b","c"};
        stream = Stream.of(strArray);

        //3、集合对象转换为一个流（Collections）：
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

    }



}
