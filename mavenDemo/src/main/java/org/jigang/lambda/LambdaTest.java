package org.jigang.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by BF100271 on 2016/5/30.
 */
public class LambdaTest {
    public static void main(String[] args) {
//        new Thread(() -> System.out.println("This is a thread")).start();

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        features.forEach(n -> {
//            if (n.equals("Lambdas")) {
//                System.out.println("I find out you");
//            }
//        });

        //features.forEach(System.out::println);

//        filter(features, str -> ((String)str).startsWith("S"));

//        filter1(features, str -> ((String) str).length() > 10);

        Predicate<String> startWithS = str -> str.startsWith("S");
        Predicate<String> tenLetterLong = str -> str.length() > 8;
        features.stream().filter(startWithS.and(tenLetterLong)).forEach(name -> System.out.println(name));

        //sum(1.12*cost)
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);


    }

    public static void filter(List features, Predicate condition) {
        for (Object feature : features) {
            if (((String) feature).length() > 10) {
                System.out.print(feature + " ");
            }
        }
    }
    public static void filter1(List features, Predicate condition) {
        features.stream().filter(name -> condition.test(name)).forEach(str -> System.out.print(str + " "));
    }
}
