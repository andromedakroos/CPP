package com.comparator.comparator.Controllers;
import com.comparator.comparator.Counter.RequestCounterThread;
import com.comparator.comparator.Entity.CalculationResult;
import com.comparator.comparator.Entity.ComparatorParams;
import com.comparator.comparator.Exceptions.NumberException;
import com.comparator.comparator.Repository.ResultRepository;
import com.comparator.comparator.Service.GuessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
public class GuessController{

    private final Logger logger = LogManager.getLogger(GuessController.class);
    private final GuessService guessService;
    private final ResultRepository resultRepository;
    public GuessController(GuessService guessService, ResultRepository resultRepository) {
        this.guessService = guessService;
        this.resultRepository = resultRepository;
    }
    @RequestMapping(value = "/guess", method = RequestMethod.GET, produces = "application/json")
    public HashMap<String, Boolean> guess(@RequestParam(name = "number")int number) throws NumberException{
        logger.info("Getting number...");

        if(number<0 || number>10){
            logger.error("Number is too high/low");
            throw new NumberException("The number can't be lower than 0 and higher than 10");
        }
        ComparatorParams comparatorParams = new ComparatorParams(number);

        RequestCounterThread requestCounterThread = new RequestCounterThread("Request-CounterThread");
        requestCounterThread.start();
        Boolean result = guessService.guessValue(comparatorParams);
        CalculationResult calculationResult = new CalculationResult(result);
        resultRepository.save(calculationResult);
        return new HashMap<>(){{
            put("result", result);
        }};
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> calculateBulkParams(@RequestBody List<ComparatorParams> bodyList) {
        List<Integer> enteredList = new LinkedList<>();
         for(ComparatorParams number:bodyList){
             enteredList.add(number.getNumber());
         }
        logger.info("Successfully postMapping");
        List<Boolean> resultList = new LinkedList<>();
        bodyList.forEach((currentElement) -> {
            resultList.add(guessService.guessValue(currentElement));
        });
        int sumResult = guessService.calculateSumOfResult(enteredList);
        int maxResult = guessService.findMaxOfResult(enteredList);
        int minResult = guessService.findMinOfResult(enteredList);

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}

