package com.comparator.comparator.Service;

import com.comparator.comparator.Entity.ComparatorParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessService {
    private int value = 0;

    public int getValue(){
            return value;
        }
    public boolean guessValue(ComparatorParams comparatorParams){
         value = (int) ( Math.random() * 10 );
        return comparatorParams.getNumber() == value;
    }
    public int calculateSumOfResult(List<Integer> resultList) {
        int sum = 0;
        if (!resultList.isEmpty()) {
            sum = resultList.stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    public int findMinOfResult(List<Integer> resultList) {
        int min = 0;
        if (!resultList.isEmpty()) {
            min = resultList.stream().mapToInt(Integer::intValue).min().getAsInt();
        }
        return min;
    }

    public int findMaxOfResult(List<Integer> resultList) {
        int max = 0;
        if (!resultList.isEmpty()) {
            max = resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        return max;
    }
}


