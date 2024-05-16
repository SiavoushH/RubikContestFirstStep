package com.rubikcontest.sheyda;


public class RubikSide {
    RubikRow centerRow;
    RubikRow bottomRow;
    RubikRow topRow;
    RubikRow centerCol;
    RubikRow leftCol;
    RubikRow rightCol;
    char[][] side;

    public char[][] getSide() {
        return side;
    }

    public void setCenterRow(RubikRow centerRow) {
        this.side[1] = centerRow.RowData;
    }
    public RubikRow getCenterRow() {
        return new RubikRow(side[1]);
    }

    public void setBottomRow(RubikRow bottomRow) {
        this.side[2] = bottomRow.RowData;
    }

    public RubikRow getBottomRow() {
        return new RubikRow(side[2]);
    }

    public void setTopRow(RubikRow topRow) {
        this.side[0] = topRow.RowData;
    }

    public RubikRow getTopRow() {
        return new RubikRow(side[0]);
    }

    public void setLeftCol(RubikRow leftCol) {
        setCol(leftCol.RowData, 0);
    }

    public RubikRow getLeftCol() {
        return new RubikRow(extractCol(side, 0));
    }

    public void setCenterCol(RubikRow centerCol) {
        setCol(centerCol.RowData, 1);
    }

    public RubikRow getCenterCol() {
        return new RubikRow(extractCol(side, 1));
    }

    public void setRightCol(RubikRow rightCol) {
        setCol(rightCol.RowData, 2);
    }

    public RubikRow getRightCol() {
        return new RubikRow(extractCol(side, 2));
    }

    public RubikSide(char[][] side) {
        this.side = side;
    }

    public char[] extractCol(char[][] side, int colNumber)
    {
        var colArray = new char[3];
        for(int row = 0; row < 3;row++)
        {
            colArray[row] = side[row][colNumber];
        }
        return colArray;
    }

    public void setCol(char[] col, int colNumber)
    {
        for(int row = 0; row < 3;row++)
        {
            this.side[row][colNumber] = col[row];
        }
    }

    public void rotateSideClockwise()
    {
        var tempLeft = getLeftCol();
        var tempCenter = getCenterCol();
        var tempRight = getRightCol();

        setTopRow(new RubikRow(reverse(tempLeft.RowData)));
        setCenterRow(new RubikRow(reverse(tempCenter.RowData)));
        setBottomRow(new RubikRow(reverse(tempRight.RowData)));
    }

    public void rotateSideAntiClockwise()
    {
        var tempLeft = getLeftCol();
        var tempCenter = getCenterCol();
        var tempRight = getRightCol();

        setTopRow(tempRight);
        setCenterRow((tempCenter));
        setBottomRow(tempLeft);
    }

    static char[] reverse(char a[])
    {
        char[] b = new char[3];
        int j = 3;
        for (int i = 0; i < 3; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        return b;
    }
}




