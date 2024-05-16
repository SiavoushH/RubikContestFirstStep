package com.rubikcontest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCase implements Runnable {

    public static final Map<String, Long> timeMap = new ConcurrentHashMap<>();
    public static final Map<String, Boolean> failedMap = new ConcurrentHashMap<>();

    private final RubikCube rubikCube;
    private final String name;
    private final String output;
    private final List<String> methodNames;

    public TestCase(RubikCube rubikCube, String name, String output, List<String> methodNames) {
        this.rubikCube = rubikCube;
        this.name = name;
        this.output = output;
        this.methodNames = methodNames;
    }

    public static void printLeaderboard() {
        System.out.println("Leaderboard:");
        //sort the timeMap by value
        //print the sorted timeMap but instead of the value print the rank

        AtomicInteger i = new AtomicInteger(1);
        timeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    if (!failedMap.getOrDefault(entry.getKey(), false)) {
                        System.out.println(i.getAndIncrement() + ". " + entry.getKey());
                    }
                });
        for (Map.Entry<String, Boolean> entry : failedMap.entrySet()) {
            System.out.println(entry.getKey() + ": At least one test failed.");
        }
    }


    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (String methodName : methodNames) {
            try {
                rubikCube.getClass().getMethod(methodName).invoke(rubikCube);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        long diff = System.nanoTime() - startTime;
        if (!convertToStringRepresentation().equals(output)) {
            failedMap.put(name, true);
//            throw new RuntimeException("Test failed for " + name + ". The correct output is: " + output + ", but the actual output is: "
//                    + convertToStringRepresentation() + ".");
        } else {
            timeMap.put(name, timeMap.getOrDefault(name, 0L) + diff);
            //System.out.println("Test passed for " + name + " with output: " + output + ".");
        }
    }

    private String convertToStringRepresentation() {
        SimpleForm simpleForm = rubikCube.toSimpleForm();
        StringBuilder sb = new StringBuilder();
        for (char[][] face : new char[][][]{
                simpleForm.getLeft(),
                simpleForm.getFront(),
                simpleForm.getTop(),
                simpleForm.getBottom(),
                simpleForm.getRight(),
                simpleForm.getBack()
        }) {
            for (char[] row : face) {
                for (char c : row) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
