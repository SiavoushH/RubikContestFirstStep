package com.rubikcontest.siavash;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Face {
    FRONT, BACK, LEFT, RIGHT, TOP, BOTTOM;

    public static List<Face> asList() {
        return new ArrayList<>(EnumSet.allOf(Face.class));
    }
}
