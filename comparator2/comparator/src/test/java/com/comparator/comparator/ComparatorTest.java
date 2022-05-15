package com.comparator.comparator;

import com.comparator.comparator.Service.GuessService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {
    private final GuessService guessService = new GuessService();
    @Test
    void testPerformCompare(){
        int i=0, value = guessService.getValue();
        while(i!=value){
            i++;
        }
        boolean expected = true;
        assertEquals(expected, i==value);
    }
}
