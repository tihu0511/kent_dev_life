package org.jigang.date;

/**
 * Created by wujigang on 16/7/8.
 */
public class DateTest {
    public static void main(String[] args) {
        long l1 = System.nanoTime();
        long l2 = System.nanoTime();
        long l3 = System.nanoTime();
        long l4 = System.nanoTime();
        long t = System.currentTimeMillis();
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(t);
    }
}
