package com.rubikcontest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PuzzleGenerator {

    private final List<String> allMoves;
    private final Map<String, List<String>> nextMoves;
    private final Map<String, String> counterMoves;

    PuzzleGenerator() {

        allMoves = List.of("rotateFrontClockwise", "rotateFrontCounterClockwise",
                "rotateBackClockwise", "rotateBackCounterClockwise", "rotateLeftUpwards", "rotateLeftDownwards",
                "rotateRightUpwards", "rotateRightDownwards", "rotateTopToRight", "rotateTopToLeft",
                "rotateBottomToLeft", "rotateBottomToRight");

        counterMoves = Map.ofEntries(
                Map.entry("rotateFrontClockwise", "rotateFrontCounterClockwise"),
                Map.entry("rotateFrontCounterClockwise", "rotateFrontClockwise"),
                Map.entry("rotateBackClockwise", "rotateBackCounterClockwise"),
                Map.entry("rotateBackCounterClockwise", "rotateBackClockwise"),
                Map.entry("rotateLeftUpwards", "rotateLeftDownwards"),
                Map.entry("rotateLeftDownwards", "rotateLeftUpwards"),
                Map.entry("rotateRightUpwards", "rotateRightDownwards"),
                Map.entry("rotateRightDownwards", "rotateRightUpwards"),
                Map.entry("rotateTopToRight", "rotateTopToLeft"),
                Map.entry("rotateTopToLeft", "rotateTopToRight"),
                Map.entry("rotateBottomToLeft", "rotateBottomToRight"),
                Map.entry("rotateBottomToRight", "rotateBottomToLeft")
        );

        List<String> frontNextMoves = createFaceMoves("Front");
        List<String> backNextMoves = createFaceMoves("Back");
        List<String> leftNextMoves = createFaceMoves("Left");
        List<String> rightNextMoves = createFaceMoves("Right");
        List<String> topNextMoves = createFaceMoves("Top");
        List<String> bottomNextMoves = createFaceMoves("Bottom");

        this.nextMoves = Map.ofEntries(
                Map.entry("rotateFrontClockwise", frontNextMoves),
                Map.entry("rotateFrontCounterClockwise", frontNextMoves),
                Map.entry("rotateBackClockwise", backNextMoves),
                Map.entry("rotateBackCounterClockwise", backNextMoves),
                Map.entry("rotateLeftUpwards", leftNextMoves),
                Map.entry("rotateLeftDownwards", leftNextMoves),
                Map.entry("rotateRightUpwards", rightNextMoves),
                Map.entry("rotateRightDownwards", rightNextMoves),
                Map.entry("rotateTopToRight", topNextMoves),
                Map.entry("rotateTopToLeft", topNextMoves),
                Map.entry("rotateBottomToLeft", bottomNextMoves),
                Map.entry("rotateBottomToRight", bottomNextMoves)
        );
    }

    public <T extends RubikCube> Puzzle getPuzzle(int numberOfMoves, Class<T> cubeClass) {
        SimpleForm initialForm = moveToRandomState(cubeClass);
        List<String> toScrambleMoves = new ArrayList<>();
        List<String> toSolveMoves = new ArrayList<>();
        try {
            T cube = cubeClass.getConstructor(SimpleForm.class).newInstance(initialForm);
            String move = null;
            for (int i = 0; i < numberOfMoves; i++) {
                move = getRandomMove(move);
                cube.getClass().getMethod(move).invoke(cube);
                toScrambleMoves.add(move);
                toSolveMoves.addFirst(counterMoves.get(move));
            }
            try {
                return Puzzle.Builder.builder()
                        .startingForm(initialForm)
                        .scrambledForm(cube.toSimpleForm())
                        .toScrambleMoves(toScrambleMoves)
                        .toSolveMoves(toSolveMoves)
                        .build();
            } catch (Exception e) {
                return this.getPuzzle(numberOfMoves, cubeClass);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> createFaceMoves(String face) {
        return allMoves.stream().filter(move -> !move.contains("rotate" + face)).toList();
    }

    private String getRandomMove(String previousMove) {
        if (previousMove != null && !counterMoves.containsKey(previousMove)) {
            return nextMoves.get(previousMove).get((int) (Math.random() * 10));
        }
        return allMoves.get((int) (Math.random() * 12));
    }

    private <T extends RubikCube> SimpleForm moveToRandomState(Class<T> cubeClass) {
        int numberOfMoves = (int) (Math.random() * 21) + 1;
        String solvedString = "gggggggggrrrrrrrrrwwwwwwwwwyyyyyyyyybbbbbbbbbooooooooo";
        SimpleForm solvedForm = new SimpleForm(solvedString);
        try {
            T cube = cubeClass.getConstructor(SimpleForm.class).newInstance(solvedForm);
            String move = null;
            for (int i = 0; i < numberOfMoves; i++) {
                move = getRandomMove(move);
                cube.getClass().getMethod(move).invoke(cube);
            }
            return cube.toSimpleForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
