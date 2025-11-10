package com.techangelx;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<Integer> nums =  Arrays.asList(2,5,6,23,7,9,45,8);
        int target = 23;
        
        List<Integer> result = LinearSearch.linearSearch(nums, target);

        System.out.println("Target is " + result.get(1)+" at index " + result.get(0));
    }
}
