package com.comparator.comparator.Counter;

public class RequestCounterThread extends Thread{
    public RequestCounterThread(String name){
        super(name);
    }

    @Override
    public void start(){
        RequestCounter.inc();
    }
}
