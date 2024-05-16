package com.rubikcontest.abolfazl;

public class RubikPage {
    private char [][] page;

    public RubikPage (char [][] page) {
        this.page = new char [3][3];
        this.page[0][0] = page[0][0];
        this.page[0][1] = page[0][1];
        this.page[0][2] = page[0][2];
        this.page[1][0] = page[1][0];
        this.page[1][1] = page[1][1];
        this.page[1][2] = page[1][2];
        this.page[2][0] = page[2][0];
        this.page[2][1] = page[2][1];
        this.page[2][2] = page[2][2];
    }

    public void rotateClockwise() {
        this.page = new char [][] {
                {page[2][0], page[1][0], page[0][0], },
                {page[2][1], page[1][1], page[0][1], },
                {page[2][2], page[1][2], page[0][2], },
        };
    }

    public void rotateCounterClockwise() {
        this.page = new char [][] {
                {page[0][2], page[1][2], page[2][2], },
                {page[0][1], page[1][1], page[2][1], },
                {page[0][0], page[1][0], page[2][0], },
        };
    }

    public char[] getRow(int row) {
        return new char[] {page[row-1][0], page[row-1][1], page[row-1][2]};
    }

    public char[] getReversedRow(int row) {
        return new char[] {page[row-1][2], page[row-1][1], page[row-1][0]};
    }

    public char[] getColumn(int column) {
        return new char[] {page[0][column - 1], page[1][column - 1], page[2][column - 1]};
    }

    public char[] getReversedColumn(int column) {
        return new char[] {page[2][column - 1], page[1][column - 1], page[0][column - 1]};
    }

    public void replaceRow(int index, char[] row) {
        this.page[index - 1][0] = row[0];
        this.page[index - 1][1] = row[1];
        this.page[index - 1][2] = row[2];
    }

    public void replaceColumn(int index, char[] column) {
        this.page[0][index - 1] = column[0];
        this.page[1][index - 1] = column[1];
        this.page[2][index - 1] = column[2];
    }

    public char[][] getPage() {
        return this.page;
    }

    public String getMatchString () {
        String match = "";
        for (char[] row : this.page) {
            for (char col : row) {
                match += col;
            }
        }
        return match;
    }
}
