package com.rubikcontest.mehdi.builder;

import com.rubikcontest.SimpleForm;
import com.rubikcontest.mehdi.datastruct.RubikDataStruct;
import com.rubikcontest.mehdi.rotator.SideRotator;

import java.util.Arrays;


public class SideBuilder implements Builder {

    private RubikDataStruct struct;
    private SideRotator rotator;

    public SideBuilder(RubikDataStruct struct, SideRotator rotator) {
        this.struct = struct;
        this.rotator = rotator;
    }

    public SimpleForm toSimpleForm() {
        return struct.toSimpleForm();
    }

    public void build(Action action) {

        if (action == Action.rotateFrontClockwise) {
            char[][] tempSide;
            char[] tempLine;

            // front
            struct.setFrontSide(rotator.rotateClockwise(struct.getFront().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getLeft().getRightLine().clone();
            tempSide[2][0] = tempLine[2];
            tempSide[2][1] = tempLine[1];
            tempSide[2][2] = tempLine[0];

            struct.setTopSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getBottom().getTopLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setLeftSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getTop().getBottomLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setRightSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getRight().getLeftLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[0][1] = tempLine[1];
            tempSide[0][2] = tempLine[0];
            struct.setBottomSide(tempSide);

            struct.flush();

        }

        if (action == Action.rotateFrontCounterClockwise) {
            char[][] tempSide;
            char[] tempLine;

            // front
            struct.setFrontSide(rotator.rotateCounterClockwise(struct.getFront().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempSide[2] = struct.getRight().getLeftLine().clone();
            struct.setTopSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getTop().getBottomLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setLeftSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getBottom().getTopLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setRightSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getLeft().getRightLine().clone();
            tempSide[0] = tempLine;
            struct.setBottomSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateBackClockwise) {
            char[][] tempSide;
            char[] tempLine;

            // back
            struct.setBackSide(rotator.rotateCounterClockwise(struct.getBack().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getLeft().getLeftLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[0][1] = tempLine[1];
            tempSide[0][2] = tempLine[0];

            struct.setTopSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getBottom().getBottomLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setLeftSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getTop().getTopLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setRightSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getRight().getRightLine().clone();
            tempSide[2][0] = tempLine[2];
            tempSide[2][1] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setBottomSide(tempSide);

            struct.flush();

        }

        if (action == Action.rotateBackCounterClockwise) {
            char[][] tempSide;
            char[] tempLine;

            // back
            struct.setBackSide(rotator.rotateClockwise(struct.getBack().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempSide[0] = struct.getRight().getRightLine().clone();
            struct.setTopSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getTop().getTopLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setLeftSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getBottom().getBottomLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setRightSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getLeft().getLeftLine().clone();
            tempSide[2] = tempLine;
            struct.setBottomSide(tempSide);

            struct.flush();

        }

        if (action == Action.rotateRightDownwards) {
            char[][] tempSide;
            char[] tempLine;

            // right
            struct.setRightSide(rotator.rotateCounterClockwise(struct.getRight().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getBack().getLeftLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setTopSide(tempSide);

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getTop().getRightLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setFrontSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getBottom().getRightLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setBackSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getFront().getRightLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setBottomSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateRightUpwards) {
            char[][] tempSide;
            char[] tempLine;

            // right
            struct.setRightSide(rotator.rotateClockwise(struct.getRight().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getFront().getRightLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setTopSide(tempSide);

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getBottom().getRightLine().clone();
            tempSide[0][2] = tempLine[0];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[2];
            struct.setFrontSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getTop().getRightLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setBackSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getBack().getLeftLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setBottomSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateLeftDownwards) {
            char[][] tempSide;
            char[] tempLine = Arrays.copyOf(struct.getBack().getTopLine(), 3);

            // left
            struct.setLeftSide(rotator.rotateClockwise(struct.getLeft().getVal()));

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getTop().getLeftLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setFrontSide(tempSide);

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getBack().getRightLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setTopSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getFront().getLeftLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setBottomSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getBottom().getLeftLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setBackSide(tempSide);

            struct.flush();

        }

        if (action == Action.rotateLeftUpwards) {
            char[][] tempSide;
            char[] tempLine;

            // right
            struct.setLeftSide(rotator.rotateCounterClockwise(struct.getLeft().getVal()));

            // top
            tempSide = getCopy(struct.getTop().getVal());
            tempLine = struct.getFront().getLeftLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setTopSide(tempSide);

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getBottom().getLeftLine().clone();
            tempSide[0][0] = tempLine[0];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[2];
            struct.setFrontSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getTop().getLeftLine().clone();
            tempSide[0][2] = tempLine[2];
            tempSide[1][2] = tempLine[1];
            tempSide[2][2] = tempLine[0];
            struct.setBackSide(tempSide);

            // bottom
            tempSide = getCopy(struct.getBottom().getVal());
            tempLine = struct.getBack().getRightLine().clone();
            tempSide[0][0] = tempLine[2];
            tempSide[1][0] = tempLine[1];
            tempSide[2][0] = tempLine[0];
            struct.setBottomSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateTopToLeft) {
            char[][] tempSide;
            char[] tempLine;

            // top
            struct.setTopSide(rotator.rotateClockwise(struct.getTop().getVal()));

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getRight().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setFrontSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getBack().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setRightSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getLeft().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setBackSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getFront().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setLeftSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateTopToRight) {
            char[][] tempSide;
            char[] tempLine;

            // top
            struct.setTopSide(rotator.rotateCounterClockwise(struct.getTop().getVal()));

            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getLeft().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setFrontSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getFront().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setRightSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getRight().getTopLine().clone();
            tempSide[0] = tempLine;
            struct.setBackSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getBack().getTopLine().clone();
            // System.out.println(tempLine);
            tempSide[0] = tempLine;
            struct.setLeftSide(tempSide);

            struct.flush();

        }

        if (action == Action.rotateBottomToLeft) {
            char[][] tempSide;
            char[] tempLine;

            // bottom
            struct.setBottomSide(rotator.rotateCounterClockwise(struct.getBottom().getVal()));


            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getRight().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setFrontSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getBack().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setRightSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getLeft().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setBackSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getFront().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setLeftSide(tempSide);

            struct.flush();
        }

        if (action == Action.rotateBottomToRight) {
            char[][] tempSide;
            char[] tempLine;

            // bottom
            struct.setBottomSide(rotator.rotateClockwise(struct.getBottom().getVal()));


            // front
            tempSide = getCopy(struct.getFront().getVal());
            tempLine = struct.getLeft().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setFrontSide(tempSide);

            // right
            tempSide = getCopy(struct.getRight().getVal());
            tempLine = struct.getFront().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setRightSide(tempSide);

            // back
            tempSide = getCopy(struct.getBack().getVal());
            tempLine = struct.getRight().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setBackSide(tempSide);

            // left
            tempSide = getCopy(struct.getLeft().getVal());
            tempLine = struct.getBack().getBottomLine().clone();
            tempSide[2] = tempLine;
            struct.setLeftSide(tempSide);

            struct.flush();
        }

    }

    private char[][] getCopy(char[][] arr) {
        return Arrays.stream(arr)
                .map(el -> Arrays.copyOf(el, el.length))
                .toArray(char[][]::new);
    }
}

