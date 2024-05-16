package com.rubikcontest;

import com.rubikcontest.siavash.SiavashCube;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestGenerator {

    public static List<Puzzle> makeTests(int count) {
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        List<Puzzle> puzzles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int numberOfMoves = (int) (Math.random() * 50) + 5;
            Puzzle puzzle = puzzleGenerator.getPuzzle(numberOfMoves, SiavashCube.class);
            //System.out.println(makeTest(i, puzzle));
            puzzles.add(puzzle);
        }
        return puzzles;
    }

    private static String makeTest(int index, Puzzle puzzle) {
        String testCase = "\nSystem.out.println(\"test" + index + " -> Number of Moves: " + puzzle.getSize() + "\");";
        testCase += "\nTestCase test" + index + " = new TestCase(" +
                "new Cube(new SimpleForm(\"" + puzzle.getStartingForm().convertToStringRepresentation() + "\")),\n" +
                "\"Test " + index + "\",\n" +
                "\"" + puzzle.getScrambledForm().convertToStringRepresentation() + "\",\n" +
                "List.of(" + puzzle.getToScrambleMoves().stream().map(move -> "\"" + move + "\"").collect(Collectors.joining(", ")) + ")\n" +
                ");\n";
        testCase += "test" + index + ".run();\n";
        return testCase;
    }
}
