package com.rubikcontest.sheyda;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;

public class SheydaCube extends RubikCube {

    RubikSide left;
    RubikSide top;
    RubikSide bottom;
    RubikSide right;
    RubikSide back;
    RubikSide front;


    public SheydaCube(SimpleForm simpleForm) {

        super(simpleForm);
        bottom = new RubikSide(simpleForm.getBottom());
        left = new RubikSide(simpleForm.getLeft());
        right = new RubikSide(simpleForm.getRight());
        back = new RubikSide(simpleForm.getBack());
        top = new RubikSide(simpleForm.getTop());
        front = new RubikSide(simpleForm.getFront());
    }

    @Override
    public void rotateFrontClockwise() {
        var topBottomRow = this.top.getBottomRow();
        var rightLeftCol = this.right.getLeftCol();
        var bottomTopRow = this.bottom.getTopRow();
        var leftRightCol = this.left.getRightCol();

        this.top.setBottomRow(new RubikRow(reverse(leftRightCol.RowData)));
        this.right.setLeftCol(topBottomRow);
        this.left.setRightCol(bottomTopRow);
        this.bottom.setTopRow(new RubikRow(reverse(rightLeftCol.RowData)));

        this.front.rotateSideClockwise();
    }


    @Override
    public void rotateFrontCounterClockwise() {
        var topBottomRow = this.top.getBottomRow();
        var rightLeftCol = this.right.getLeftCol();
        var bottomTopRow = this.bottom.getTopRow();
        var leftRightCol = this.left.getRightCol();

        this.top.setBottomRow(rightLeftCol);
        this.right.setLeftCol(new RubikRow(reverse(bottomTopRow.RowData)));
        this.left.setRightCol(new RubikRow(reverse(topBottomRow.RowData)));
        this.bottom.setTopRow(leftRightCol);

        this.front.rotateSideAntiClockwise();

    }

    @Override
    public void rotateBackClockwise() {
        var topTopRow = this.top.getTopRow();
        var rightRightCol = this.right.getRightCol();
        var bottomBottomRow = this.bottom.getBottomRow();
        var leftLeftCol = this.left.getLeftCol();

        this.top.setTopRow(new RubikRow(reverse(leftLeftCol.RowData)));
        this.right.setRightCol(topTopRow);
        this.left.setLeftCol(bottomBottomRow);
        this.bottom.setBottomRow(new RubikRow(reverse(rightRightCol.RowData)));


        this.back.rotateSideAntiClockwise();

    }

    @Override
    public void rotateBackCounterClockwise() {
        var topTopRow = this.top.getTopRow();
        var rightRightCol = this.right.getRightCol();
        var bottomBottomRow = this.bottom.getBottomRow();
        var leftLeftCol = this.left.getLeftCol();

        this.top.setTopRow(rightRightCol);
        this.right.setRightCol(new RubikRow(reverse(bottomBottomRow.RowData)));
        this.left.setLeftCol(new RubikRow(reverse(topTopRow.RowData)));
        this.bottom.setBottomRow(leftLeftCol);


        this.back.rotateSideClockwise();

    }

    @Override
    public void rotateLeftUpwards() {

        var topLeftCol = this.top.getLeftCol();
        var frontLeftCol = this.front.getLeftCol();
        var bottomLeftCol = this.bottom.getLeftCol();
        var backLeftCol = this.back.getRightCol();


        this.top.setLeftCol(frontLeftCol);
        this.front.setLeftCol(bottomLeftCol);
        this.bottom.setLeftCol(new RubikRow(reverse(backLeftCol.RowData)));
        this.back.setRightCol(new RubikRow(reverse(topLeftCol.RowData)));

        this.left.rotateSideAntiClockwise();

    }

    @Override
    public void rotateLeftDownwards() {

        var topLeftCol = this.top.getLeftCol();
        var frontLeftCol = this.front.getLeftCol();
        var bottomLeftCol = this.bottom.getLeftCol();
        var backLeftCol = this.back.getRightCol();


        this.top.setLeftCol(new RubikRow(reverse(backLeftCol.RowData)));
//        this.top.setLeftCol(backLeftCol);
        this.front.setLeftCol(topLeftCol);
        this.bottom.setLeftCol(frontLeftCol);
        this.back.setRightCol(new RubikRow(reverse(bottomLeftCol.RowData)));
//        this.back.setRightCol(bottomLeftCol);

        this.left.rotateSideClockwise();
    }

    @Override
    public void rotateRightUpwards() {
        var topRightCol = this.top.getRightCol();
        var frontRightCol = this.front.getRightCol();
        var bottomRightCol = this.bottom.getRightCol();
        var backRightCol = this.back.getLeftCol();

        this.top.setRightCol(frontRightCol);
        this.front.setRightCol(bottomRightCol);
        this.bottom.setRightCol(new RubikRow(reverse(backRightCol.RowData)));
        this.back.setLeftCol(new RubikRow(reverse(topRightCol.RowData)));

        this.right.rotateSideClockwise();

    }

    @Override
    public void rotateRightDownwards() {

        var topRightCol = this.top.getRightCol();
        var frontRightCol = this.front.getRightCol();
        var bottomRightCol = this.bottom.getRightCol();
        var backRightCol = this.back.getLeftCol();

        this.top.setRightCol(new RubikRow(reverse(backRightCol.RowData)));
        this.front.setRightCol(topRightCol);
        this.bottom.setRightCol(frontRightCol);
        this.back.setLeftCol(new RubikRow(reverse(bottomRightCol.RowData)));

//        this.top.setRightCol(backRightCol);
//        this.back.setLeftCol(bottomRightCol));

        this.right.rotateSideAntiClockwise();
    }

    @Override
    public void rotateTopToRight() {

        var rightTopRow = this.right.getTopRow();
        var frontTopRow = this.front.getTopRow();
        var leftTopRoe = this.left.getTopRow();
        var backTopRow = this.back.getTopRow();

        this.right.setTopRow(frontTopRow);
        this.front.setTopRow(leftTopRoe);
//        this.left.setTopRow(new RubikRow(reverse(backTopRow.RowData)));
//        this.back.setTopRow(new RubikRow(reverse(rightTopRow.RowData)));

        this.left.setTopRow(backTopRow);
        this.back.setTopRow(rightTopRow);

        this.top.rotateSideAntiClockwise();

    }

    @Override
    public void rotateTopToLeft() {

        var rightTopRow = this.right.getTopRow();
        var frontTopRow = this.front.getTopRow();
        var leftTopRoe = this.left.getTopRow();
        var backTopRow = this.back.getTopRow();

        this.right.setTopRow(backTopRow);
        this.front.setTopRow(rightTopRow);
        this.left.setTopRow(frontTopRow);
        this.back.setTopRow(leftTopRoe);

        this.top.rotateSideClockwise();
    }

    @Override
    public void rotateBottomToLeft() {

        var rightBottomRow = this.right.getBottomRow();
        var frontBottomRow = this.front.getBottomRow();
        var leftBottomRoe = this.left.getBottomRow();
        var backBottomRow = this.back.getBottomRow();

        this.right.setBottomRow(backBottomRow);
        this.front.setBottomRow(rightBottomRow);
        this.left.setBottomRow(frontBottomRow);
        this.back.setBottomRow(leftBottomRoe);

        this.bottom.rotateSideAntiClockwise();

    }

    @Override
    public void rotateBottomToRight() {

        var rightBottomRow = this.right.getBottomRow();
        var frontBottomRow = this.front.getBottomRow();
        var leftBottomRoe = this.left.getBottomRow();
        var backBottomRow = this.back.getBottomRow();

        this.right.setBottomRow(frontBottomRow);
        this.front.setBottomRow(leftBottomRoe);
        this.left.setBottomRow(backBottomRow);
        this.back.setBottomRow(rightBottomRow);

        this.bottom.rotateSideClockwise();

    }

    @Override
    public boolean equals(RubikCube other) {
        return other.toSimpleForm().equals(this.toSimpleForm());
    }

    @Override
    public SimpleForm toSimpleForm() {
        return new SimpleForm(this.left.side, this.front.side, this.top.side, this.bottom.side, this.right.side, this.back.side);
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {

    }

    static char[] reverse(char a[]) {
        char[] b = new char[3];
        int j = 3;
        for (int i = 0; i < 3; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        return b;
    }


}
