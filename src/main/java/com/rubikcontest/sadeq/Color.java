package com.rubikcontest.sadeq;

public enum Color {
    Green('g'),
    White('w'),
    Red('r'),
    Yellow('y'),
    Blue('b'),
    Orange('o'),
    Unknown('u');

    private final char value;

    private Color(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Color fromValue(char value) {
        return switch (value) {
            case 'g' -> Green;
            case 'w' -> White;
            case 'r' -> Red;
            case 'y' -> Yellow;
            case 'b' -> Blue;
            case 'o' -> Orange;
            default -> Unknown;
        };
    }
}
