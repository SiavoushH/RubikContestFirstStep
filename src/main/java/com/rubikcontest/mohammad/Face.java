package com.rubikcontest.mohammad;

import java.util.List;

class Face {
    private List<Piece> pieces;
    private RubikColor color;
    private Axis axis;

    public Face(Axis axis, List<Piece> pieces, char[][] colors) {
        this.axis = axis;
        this.pieces = pieces;
        this.color = RubikColor.valueOf(String.valueOf(colors[1][1]));
        for (Piece piece : this.pieces) {
            int i = 0;
            int j = 0;
            switch (axis) {
                case Z:
                    i = (piece.getY() - 1) * -1;
                    j = piece.getX() + 1;
                    break;
                case MX:
                    i = (piece.getY() - 1) * -1;
                    j = piece.getZ() + 1;
                    break;
                case X:
                    i = (piece.getY() - 1) * -1;
                    j = (piece.getZ() - 1) * -1;
                    break;
                case MZ:
                    i = (piece.getY() - 1) * -1;
                    j = (piece.getX() - 1) * -1;
                    break;
                case Y:
                    i = piece.getZ() + 1;
                    j = piece.getX() + 1;
                    break;
                case MY:
                    i = (piece.getZ() - 1) * -1;
                    j = piece.getX() + 1;
                    break;
            }
            piece.setColorForAxis(Axis.getPositiveAxis(axis), RubikColor.valueOf(String.valueOf(colors[i][j])));
        }
    }

    public void rotateClockwize() {
        switch (axis) {
            case Z:
            case MZ:
                for (Piece piece : pieces) {
                    piece.rotateClockwizeAroundZAxis();
                }
                break;
            case X:
                for (Piece piece : pieces) {
                    piece.rotateClockwizeAroundXAxis();
                }
                break;
            case MX:
                for (Piece piece : pieces) {
                    piece.rotateCounterClockwizeAroundXAxis();
                }
                break;
            case Y:
                for (Piece piece : pieces) {
                    piece.rotateClockwizeAroundYAxis();
                }
                break;
            case MY:
                for (Piece piece : pieces) {
                    piece.rotateCounterClockwizeAroundYAxis();
                }
                break;
        }

    }

    public void rotateCounterClockwize() {
        switch (axis) {
            case Z:
            case MZ:
                for (Piece piece : pieces) {
                    piece.rotateCounterClockwizeAroundZAxis();
                }
                break;
            case X:
                for (Piece piece : pieces) {
                    piece.rotateCounterClockwizeAroundXAxis();
                }
                break;
            case MX:
                for (Piece piece : pieces) {
                    piece.rotateClockwizeAroundXAxis();
                }
                break;
            case Y:
                for (Piece piece : pieces) {
                    piece.rotateCounterClockwizeAroundYAxis();
                }
                break;
            case MY:
                for (Piece piece : pieces) {
                    piece.rotateClockwizeAroundYAxis();
                }
                break;
        }

    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        switch (axis) {
            case Z:
                for (int i = 1; i >= -1; i--) {
                    int finalI = i;
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) { sb.append(this.color.toString()); continue; }

                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getX() == finalJ && piece.getY() == finalI).findFirst().get().getColorForAxis(Axis.Z).toString());
                    }
                }
                break;
            case MZ:
                for (int i = 1; i >= -1; i--) {
                    int finalI = i;
                    for (int j = 1; j >= -1; j--) {
                        if (i == 0 && j == 0) { sb.append(this.color.toString()); continue; }

                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getX() == finalJ && piece.getY() == finalI).findFirst().get().getColorForAxis(Axis.Z).toString());
                    }
                }
                break;
            case X:
                for (int i = 1; i >= -1; i--) {
                    int finalI = i;
                    for (int j = 1; j >= -1; j--) {
                        if (i == 0 && j == 0) {  sb.append(this.color.toString()); continue; }

                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getZ() == finalJ && piece.getY() == finalI).findFirst().get().getColorForAxis(Axis.X).toString());
                    }
                }
                break;
            case MX:
                for (int i = 1; i >= -1; i--) {
                    int finalI = i;
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) { sb.append(this.color.toString()); continue; }
                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getZ() == finalJ && piece.getY() == finalI).findFirst().get().getColorForAxis(Axis.X).toString());
                    }
                }
                break;
            case Y:
                for (int i = -1; i <= 1; i++) {
                    int finalI = i;
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) { sb.append(this.color.toString()); continue; }

                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getX() == finalJ && piece.getZ() == finalI).findFirst().get().getColorForAxis(Axis.Y).toString());
                    }
                }
                break;
            case MY:
                for (int i = 1; i >= -1; i--) {
                    int finalI = i;
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) { sb.append(this.color.toString()); continue; }

                        int finalJ = j;
                        sb.append(this.pieces.stream().filter(piece -> piece.getX() == finalJ && piece.getZ() == finalI).findFirst().get().getColorForAxis(Axis.Y).toString());
                    }
                }
                break;
        }

        return sb.toString();
    }
}
