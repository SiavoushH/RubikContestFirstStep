package com.rubikcontest.mohammad;

public enum Axis {
    X, Y, Z, MX, MY, MZ;

    public static Axis getPositiveAxis(Axis axis) {
        return switch (axis) {
            case X, MX -> X;
            case Y, MY -> Y;
            default -> Z;
        };
    }
}
