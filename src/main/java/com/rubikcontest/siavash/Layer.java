package com.rubikcontest.siavash;

import java.util.*;

public class Layer {
    private final Color color;
    private final Axis axis;
    private final Face face;
    private final Set<Position> positions;
    private final Set<Cubelet> cubelets;
    private final Map<Position, Cubelet> cubeletsByPosition;

    private Layer(Builder builder) {
        color = builder.color;
        axis = builder.axis;
        face = builder.face;
        cubeletsByPosition = new HashMap<>();
        if (builder.positions == null || builder.cubelets == null) {
            positions = new HashSet<>();
            cubelets = new HashSet<>();
        } else {
            positions = builder.positions;
            cubelets = builder.cubelets;
            attachCubeletsToPositions();
        }
        fillPositions();
    }

    public Face getFace() {
        return face;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public Set<Cubelet> rotate(RotationInstruction rotationInstruction) {
        for (Cubelet cubelet : cubelets) {
            cubelet.rotate(rotationInstruction);
            cubeletsByPosition.put(cubelet.getPosition(), cubelet);
        }
        return cubelets;
    }

    public void addCubelet(Cubelet cubelet) {
        if (positions.contains(cubelet.getPosition())) {
            cubelets.add(cubelet);
            cubeletsByPosition.put(cubelet.getPosition(), cubelet);
        }
    }

    public void updateWithCubelets(Set<Cubelet> cubelets) {
        removeUnassociatedCubelets();
        for (Cubelet cubelet : cubelets) {
            addCubelet(cubelet);
        }
    }


    public int[] mapPositionToIndexes(Position position) {
        return switch (face) {
            case FRONT -> new int[]{2 - position.y(), position.x()};
            case BACK -> new int[]{2 - position.y(), 2 - position.x()};
            case LEFT -> new int[]{2 - position.y(), 2 - position.z()};
            case RIGHT -> new int[]{2 - position.y(), position.z()};
            case TOP -> new int[]{2 - position.z(), position.x()};
            case BOTTOM -> new int[]{position.z(), position.x()};
        };
    }

    public char[][] buildFaceArray() {
        char[][] faceArray = new char[3][3];
        for (Position position : positions) {
            int[] indexes = mapPositionToIndexes(position);
            faceArray[indexes[0]][indexes[1]] = cubeletsByPosition.get(position).getColorByFace(face).getColor();
        }
        return faceArray;
    }

    private void attachCubeletsToPositions() {
        for (Cubelet cubelet : cubelets) {
            cubeletsByPosition.put(cubelet.getPosition(), cubelet);
        }
    }

    private void fillPositions() {
        positions.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] indexes = calculateIndexes(i, j);
                Position position = new Position(indexes[0], indexes[1], indexes[2]);
                positions.add(position);
            }
        }
    }

    private int[] calculateIndexes(int i, int j) {
        Map<Face, int[]> faceToIndexes = Map.of(
                Face.FRONT, new int[]{i, j, 0},
                Face.BACK, new int[]{i, j, 2},
                Face.LEFT, new int[]{0, i, j},
                Face.RIGHT, new int[]{2, i, j},
                Face.TOP, new int[]{i, 2, j},
                Face.BOTTOM, new int[]{i, 0, j}
        );
        return faceToIndexes.get(face);
    }

    private void removeUnassociatedCubelets() {
        Set<Cubelet> cubeletsToRemove = new HashSet<>();
        for (Cubelet cubelet : cubelets) {
            if (!positions.contains(cubelet.getPosition())) {
                cubeletsToRemove.add(cubelet);
                cubeletsByPosition.remove(cubelet.getPosition());
            }
        }
        cubelets.removeAll(cubeletsToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Layer layer)) return false;
        return color == layer.color && axis == layer.axis && face == layer.face;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, axis, face);
    }

    @Override
    public String toString() {
        return "Layer{" +
                "color=" + color +
                ", axis=" + axis +
                ", face=" + face +
                ", positions=" + positions +
                ", cubelets=" + cubelets +
                ", cubeletsByPosition=" + cubeletsByPosition +
                '}';
    }

    public static final class Builder {
        private Color color;
        private Axis axis;
        private Face face;
        private Set<Position> positions;
        private Set<Cubelet> cubelets;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder color(Color val) {
            color = val;
            return this;
        }

        public Builder axis(Axis val) {
            axis = val;
            return this;
        }

        public Builder face(Face val) {
            face = val;
            return this;
        }

        public Builder positions(Set<Position> val) {
            positions = val;
            return this;
        }

        public Builder cubelets(Set<Cubelet> val) {
            cubelets = val;
            return this;
        }

        public Layer build() {
            return new Layer(this);
        }
    }
}
