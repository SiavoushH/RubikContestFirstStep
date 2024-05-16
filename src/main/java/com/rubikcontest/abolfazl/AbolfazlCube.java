package com.rubikcontest.abolfazl;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;

public class AbolfazlCube extends RubikCube {

    private RubikPage front;
    private RubikPage back;
    private RubikPage top;
    private RubikPage bottom;
    private RubikPage left;
    private RubikPage right;

    public AbolfazlCube(SimpleForm simpleForm) {
        super(simpleForm);
        front = new RubikPage(simpleForm.getFront());
        back = new RubikPage(simpleForm.getBack());
        top = new RubikPage(simpleForm.getTop());
        bottom = new RubikPage(simpleForm.getBottom());
        left = new RubikPage(simpleForm.getLeft());
        right = new RubikPage(simpleForm.getRight());
    }

    @Override
    public void rotateFrontClockwise() {
        front.rotateClockwise();
        char[] temp = top.getRow(3);
        top.replaceRow(3, left.getReversedColumn(3));
        left.replaceColumn(3, bottom.getRow(1));
        bottom.replaceRow(1, right.getReversedColumn(1));
        right.replaceColumn(1, temp);
    }

    @Override
    public void rotateFrontCounterClockwise() {
        front.rotateCounterClockwise();
        char[] temp = top.getReversedRow(3);
        top.replaceRow(3, right.getColumn(1));
        right.replaceColumn(1, bottom.getReversedRow(1));
        bottom.replaceRow(1, left.getColumn(3));
        left.replaceColumn(3, temp);
    }

    @Override
    public void rotateBackClockwise() {
        this.back.rotateCounterClockwise();
        char[] temp = this.top.getRow(1);
        this.top.replaceRow(1, this.left.getReversedColumn(1));
        this.left.replaceColumn(1, this.bottom.getRow(3));
        this.bottom.replaceRow(3, this.right.getReversedColumn(3));
        this.right.replaceColumn(3, temp);
    }

    @Override
    public void rotateBackCounterClockwise() {
        this.back.rotateClockwise();
        char[] temp = this.top.getReversedRow(1);
        this.top.replaceRow(1, this.right.getColumn(3));
        this.right.replaceColumn(3, this.bottom.getReversedRow(3));
        this.bottom.replaceRow(3, this.left.getColumn(1));
        this.left.replaceColumn(1, temp);
    }

    @Override
    public void rotateLeftUpwards() {
        this.left.rotateCounterClockwise();
        char[] temp = this.front.getColumn(1);
        this.front.replaceColumn(1, this.bottom.getColumn(1));
        this.bottom.replaceColumn(1, this.back.getReversedColumn(3));
        this.back.replaceColumn(3, this.top.getReversedColumn(1));
        this.top.replaceColumn(1, temp);
    }

    @Override
    public void rotateLeftDownwards() {
        this.left.rotateClockwise();
        char[] temp = this.front.getColumn(1);
        this.front.replaceColumn(1, this.top.getColumn(1));
        this.top.replaceColumn(1, this.back.getReversedColumn(3));
        this.back.replaceColumn(3, this.bottom.getReversedColumn(1));
        this.bottom.replaceColumn(1, temp);
    }

    @Override
    public void rotateRightUpwards() {
        this.right.rotateClockwise();
        char[] temp = this.front.getColumn(3);
        this.front.replaceColumn(3, this.bottom.getColumn(3));
        this.bottom.replaceColumn(3, this.back.getReversedColumn(1));
        this.back.replaceColumn(1, this.top.getReversedColumn(3));
        this.top.replaceColumn(3, temp);
    }

    @Override
    public void rotateRightDownwards() {
        this.right.rotateCounterClockwise();
        char[] temp = this.front.getColumn(3);
        this.front.replaceColumn(3, this.top.getColumn(3));
        this.top.replaceColumn(3, this.back.getReversedColumn(1));
        this.back.replaceColumn(1, bottom.getReversedColumn(3));
        this.bottom.replaceColumn(3, temp);
    }

    @Override
    public void rotateTopToRight() {
        this.top.rotateCounterClockwise();
        char[] temp = this.front.getRow(1);
        this.front.replaceRow(1, this.left.getRow(1));
        this.left.replaceRow(1, this.back.getRow(1));
        this.back.replaceRow(1, this.right.getRow(1)); // ?
        this.right.replaceRow(1, temp);
    }

    @Override
    public void rotateTopToLeft() {
        this.top.rotateClockwise();
        char[] temp = this.front.getRow(1);
        this.front.replaceRow(1, this.right.getRow(1));
        this.right.replaceRow(1, this.back.getRow(1));
        this.back.replaceRow(1, this.left.getRow(1));
        this.left.replaceRow(1, temp);
    }

    @Override
    public void rotateBottomToLeft() {
        this.bottom.rotateCounterClockwise();
        char[] temp = this.front.getRow(3);
        this.front.replaceRow(3, this.right.getRow(3));
        this.right.replaceRow(3, this.back.getRow(3));
        this.back.replaceRow(3, this.left.getRow(3));
        this.left.replaceRow(3, temp);
    }

    @Override
    public void rotateBottomToRight() {
        this.bottom.rotateClockwise();
        char[] temp = this.front.getRow(3);
        this.front.replaceRow(3, this.left.getRow(3));
        this.left.replaceRow(3, this.back.getRow(3));
        this.back.replaceRow(3, this.right.getRow(3));
        this.right.replaceRow(3, temp);
    }

    @Override
    public boolean equals(RubikCube other) {
        return false;
    }

    @Override
    public SimpleForm toSimpleForm() {
        return new SimpleForm(
                this.left.getPage(),
                this.front.getPage(),
                this.top.getPage(),
                this.bottom.getPage(),
                this.right.getPage(),
                this.back.getPage()
        );
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {
        front = new RubikPage(simpleForm.getFront());
        back = new RubikPage(simpleForm.getBack());
        top = new RubikPage(simpleForm.getTop());
        bottom = new RubikPage(simpleForm.getBottom());
        left = new RubikPage(simpleForm.getLeft());
        right = new RubikPage(simpleForm.getRight());
    }

    public String getMathString () {
        return this.left.getMatchString() + this.front.getMatchString() + this.top.getMatchString() + this.bottom.getMatchString() + right.getMatchString() + this.back.getMatchString();
    }
}
