package com.rubikcontest.siavash;

public enum Color {
    GREEN('g'),
    RED('r'),
    WHITE('w'),
    YELLOW('y'),
    BLUE('b'),
    ORANGE('o'),
    HIDDEN('h');

    private final char color;

    Color(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public static Color valueOf(char color) {
        for (Color c : Color.values()) {
            if (c.color == color) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid color: " + color);
    }
}
