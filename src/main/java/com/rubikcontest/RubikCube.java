package com.rubikcontest;

public abstract class RubikCube {

    public RubikCube(SimpleForm simpleForm) {
        initUsingSimpleForm(simpleForm);
    }

    public abstract void rotateFrontClockwise();
    public abstract void rotateFrontCounterClockwise();

    public abstract void rotateBackClockwise();
    public abstract void rotateBackCounterClockwise();

    public abstract void rotateLeftUpwards();
    public abstract void rotateLeftDownwards();

    public abstract void rotateRightUpwards();
    public abstract void rotateRightDownwards();

    public abstract void rotateTopToRight();
    public abstract void rotateTopToLeft();

    public abstract void rotateBottomToLeft();
    public abstract void rotateBottomToRight();

    public abstract boolean equals(RubikCube other);

    public abstract SimpleForm toSimpleForm();

    protected abstract void initUsingSimpleForm(SimpleForm simpleForm);
}