package me.lutuk.utils;


public class CalcUtils {
    public static double positveStats(double max, double min, double current, double weight){
        return  (100 / (max - min)) * (current - min) * (weight / 100);
    }
    public static double negativeStats(double max, double min, double current, double weight){
        return (100 / (min - max) * (max - current) + 100) * (weight / 100);
    }

    public static void main(String[] args) {
        System.out.println(positveStats(35,8,29,10));
        System.out.println(positveStats(13,3,7,5));
        System.out.println(negativeStats(351,189,276,15));
        System.out.println(positveStats(371,89,114,32.5));
        System.out.println(positveStats(39,9,39,37.5));
    }
}
