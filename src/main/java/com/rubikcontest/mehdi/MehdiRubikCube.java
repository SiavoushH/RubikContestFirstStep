package com.rubikcontest.mehdi;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;
import com.rubikcontest.mehdi.builder.Builder;
import com.rubikcontest.mehdi.builder.SideBuilder;
import com.rubikcontest.mehdi.datastruct.RubikArrayDataStruct;
import com.rubikcontest.mehdi.datastruct.RubikDataStruct;
import com.rubikcontest.mehdi.rotator.SideRotator;
import com.rubikcontest.mehdi.rotator.SimpleSideRotator;

public class MehdiRubikCube extends RubikCube {

    private SideRotator rotator;
    private SideBuilder builder;
    private RubikDataStruct struct;

    public MehdiRubikCube(SimpleForm simpleForm) {
        super(simpleForm);
        this.rotator = new SimpleSideRotator();
        this.struct = new RubikArrayDataStruct();
        this.struct.feed(simpleForm);
        this.builder = new SideBuilder(struct, rotator);
    }

    @Override
    public void rotateFrontClockwise() {
        builder.build(Builder.Action.rotateFrontClockwise);
    }

    @Override
    public void rotateFrontCounterClockwise() {
        builder.build(Builder.Action.rotateFrontCounterClockwise);
    }

    @Override
    public void rotateBackClockwise() {
        builder.build(Builder.Action.rotateBackClockwise);
    }

    @Override
    public void rotateBackCounterClockwise() {
        builder.build(Builder.Action.rotateBackCounterClockwise);
    }

    @Override
    public void rotateLeftUpwards() {
        builder.build(Builder.Action.rotateLeftUpwards);
    }

    @Override
    public void rotateLeftDownwards() {
        builder.build(Builder.Action.rotateLeftDownwards);
    }

    @Override
    public void rotateRightUpwards() {
        builder.build(Builder.Action.rotateRightUpwards);
    }

    @Override
    public void rotateRightDownwards() {
        builder.build(Builder.Action.rotateRightDownwards);
    }

    @Override
    public void rotateTopToRight() {
        builder.build(Builder.Action.rotateTopToRight);
    }

    @Override
    public void rotateTopToLeft() {
        builder.build(Builder.Action.rotateTopToLeft);
    }

    @Override
    public void rotateBottomToLeft() {
        builder.build(Builder.Action.rotateBottomToLeft);
    }

    @Override
    public void rotateBottomToRight() {
        builder.build(Builder.Action.rotateBottomToRight);
    }

    @Override
    public boolean equals(RubikCube other) {
        return true;
    }

    @Override
    public SimpleForm toSimpleForm() {
        return builder.toSimpleForm();
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {

    }

}