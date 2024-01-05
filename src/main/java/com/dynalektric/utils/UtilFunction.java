package com.dynalektric.utils;

public class UtilFunction {
    public static double RoundedToNDecimal(double answer, int round) {
        return (double) Math.round(answer * Math.pow(10, round)) / Math.pow(10, round);
    }
}
