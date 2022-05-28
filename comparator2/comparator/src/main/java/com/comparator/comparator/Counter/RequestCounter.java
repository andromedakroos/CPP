package com.comparator.comparator.Counter;

public class RequestCounter {
    private static int requests = 0;

    public static synchronized void inc() {
        requests++;
    }

    public static int getRequestsCount() {
        return requests;
    }
}

