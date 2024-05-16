package com.rubikcontest;

import com.rubikcontest.abolfazl.AbolfazlCube;
import com.rubikcontest.mehdi.MehdiRubikCube;
import com.rubikcontest.mohammad.MohammadRubikCube;
import com.rubikcontest.sadeq.SadeqCube;
import com.rubikcontest.sheyda.SheydaCube;
import com.rubikcontest.siavash.SiavashCube;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        List<Puzzle> puzzles = TestGenerator.makeTests(10000);
        List<TestCase> testCases = new ArrayList<>();

        for (Puzzle puzzle : puzzles) {
            SimpleForm startingForm = puzzle.getStartingForm();
            TestCase t1 = new TestCase(new AbolfazlCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Abolfazl",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            TestCase t2 = new TestCase(new MehdiRubikCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Mehdi",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            TestCase t3 = new TestCase(new MohammadRubikCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Mohammad",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            TestCase t4 = new TestCase(new SadeqCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Sadeq",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            TestCase t5 = new TestCase(new SheydaCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Sheyda",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            TestCase t6 = new TestCase(new SiavashCube(new SimpleForm(startingForm.convertToStringRepresentation())),
                    "Siavash",
                    puzzle.getScrambledForm().convertToStringRepresentation(),
                    puzzle.getToScrambleMoves());
            testCases.add(t1);
            testCases.add(t2);
            testCases.add(t3);
            testCases.add(t4);
            testCases.add(t5);
            testCases.add(t6);
        }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
        for (TestCase testCase : testCases) {
            executor.execute(testCase);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.DAYS)) {
                System.out.println("Executor did not terminate in the specified time.");
                List<Runnable> droppedTasks = executor.shutdownNow();
                System.out.println("Dropped " + droppedTasks.size() + " tasks");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finished all threads");
        TestCase.printLeaderboard();
    }
}