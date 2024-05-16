package com.rubikcontest.mehdi.builder;

public interface Builder {
    enum Side {
        TOP,
        BOTTOM,
        FRONT,
        BACK,
        LEFT,
        RIGHT,
    }

    enum Action {
        rotateFrontClockwise,
        rotateFrontCounterClockwise,
        rotateBackClockwise,
        rotateBackCounterClockwise,
        rotateLeftUpwards,
        rotateLeftDownwards,
        rotateRightUpwards,
        rotateRightDownwards,
        rotateTopToRight,
        rotateTopToLeft,
        rotateBottomToLeft,
        rotateBottomToRight,
    }

    public void build(Action action);
}
