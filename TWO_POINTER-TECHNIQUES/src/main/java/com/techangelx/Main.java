package com.techangelx;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(2, 2, 1, 1, 3);
        int k = 3;

        int result = MinimumLengthSubarray.findMinimumLengthSubarray(arr, k);

        System.out.println("Minimum length = " + result);
    }
}
