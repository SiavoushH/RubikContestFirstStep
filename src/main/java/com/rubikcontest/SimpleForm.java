package com.rubikcontest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SimpleForm {

    // each face is a 3x3 array of chars
    char[][] left;
    char[][] front;
    char[][] top;
    char[][] bottom;
    char[][] right;
    char[][] back;

    public SimpleForm(char[][] left,
                      char[][] front,
                      char[][] top,
                      char[][] bottom,
                      char[][] right,
                      char[][] back) {
        this.left = left;
        this.front = front;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.back = back;

        validate();
    }

    public SimpleForm(String representation) {
        if (representation.length() != 54) {
            throw new RuntimeException("Invalid representation: " + representation);
        }
        Map<Character, Integer> validColors = new HashMap<>();
        validColors.put('g', 0);
        validColors.put('r', 0);
        validColors.put('w', 0);
        validColors.put('y', 0);
        validColors.put('b', 0);
        validColors.put('o', 0);

        char[][] allFaces = new char[6][9];
        int index = 0;
        for (char c : representation.toCharArray()) {
            if (!validColors.containsKey(c)) {
                throw new RuntimeException("Invalid representation: " + representation);
            }
            validColors.put(c, validColors.get(c) + 1);
            allFaces[index / 9][index % 9] = c;
            index++;
        }
        for (int count: validColors.values()) {
            if (count != 9) {
                throw new RuntimeException("Invalid representation: " + representation);
            }
        }
        left = extractFace(allFaces, 0);
        front = extractFace(allFaces, 1);
        top = extractFace(allFaces, 2);
        bottom = extractFace(allFaces, 3);
        right = extractFace(allFaces, 4);
        back = extractFace(allFaces, 5);
    }

    public char[][] getLeft() {
        return left;
    }

    public char[][] getFront() {
        return front;
    }

    public char[][] getTop() {
        return top;
    }

    public char[][] getBottom() {
        return bottom;
    }

    public char[][] getRight() {
        return right;
    }

    public char[][] getBack() {
        return back;
    }

    public String convertToStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (char[][] face : new char[][][]{left, front, top, bottom, right, back}) {
            for (char[] row : face) {
                for (char c : row) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * I removed the implementation. This method checks the
     * validity of the given Rubik's cube. for example, you
     * can't have an edge that connects a yellow to a white
     *
     * @throws RuntimeException Given Rubik's cube is invalid.
     */
    private void validate() {
        return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleForm that)) return false;
        return Objects.deepEquals(left, that.left)
                && Objects.deepEquals(front, that.front)
                && Objects.deepEquals(top, that.top)
                && Objects.deepEquals(bottom, that.bottom)
                && Objects.deepEquals(right, that.right)
                && Objects.deepEquals(back, that.back);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Arrays.deepHashCode(left),
                Arrays.deepHashCode(front),
                Arrays.deepHashCode(top),
                Arrays.deepHashCode(bottom),
                Arrays.deepHashCode(right),
                Arrays.deepHashCode(back)
        );
    }

    @Override
    public String toString() {
        return "SimpleForm{" +
                "left=" + faceToString(left) +
                ", front=" + faceToString(front) +
                ", top=" + faceToString(top) +
                ", bottom=" + faceToString(bottom) +
                ", right=" + faceToString(right) +
                ", back=" + faceToString(back) +
                '}';
    }

    private char[][] extractFace(char[][] allFaces, int faceIndex) {
        char[][] face = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(allFaces[faceIndex], i * 3, face[i], 0, 3);
        }
        return face;
    }

    private String faceToString(char[][] face) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (char[] chars : face) {
            for (char aChar : chars) {
                sb.append(aChar);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
