package com.rubikcontest.mehdi.rotator;

public class SimpleSideRotator implements SideRotator {

    @Override
    public char[][] rotateClockwise(char[][] side) {
        char[][] res = {
                { side[2][0], side[1][0], side[0][0] },
                { side[2][1], side[1][1], side[0][1] },
                { side[2][2], side[1][2], side[0][2] }
        };

        return res;
    }

    @Override
    public char[][] rotateCounterClockwise(char[][] side) {
        char[][] res = {
                { side[0][2], side[1][2], side[2][2] },
                { side[0][1], side[1][1], side[2][1] },
                { side[0][0], side[1][0], side[2][0] }
        };

        return res;
    }

}
