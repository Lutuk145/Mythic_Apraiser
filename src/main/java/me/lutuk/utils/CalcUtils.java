package me.lutuk.utils;


public class CalcUtils {
    public static double positveStats(double max, double min, double current, double weight){
        return  (100 / (max - min)) * (current - min) * (weight / 100);
    }
    public static double negativeStats(double max, double min, double current, double weight){
        return (100 / (min - max) * (max - current) + 100) * (weight / 100);
    }
}
