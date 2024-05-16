package com.rubikcontest.siavash;

public record Position(int x, int y, int z) {

    public Position rotate(Axis axis, Direction direction) {
        return switch (axis) {
            case X ->
                    new Position(x, direction == Direction.CLOCKWISE ? z : 2 - z, direction == Direction.CLOCKWISE ? 2 - y : y);
            case Y ->
                    new Position(direction == Direction.CLOCKWISE ? 2 - z : z, y, direction == Direction.CLOCKWISE ? x : 2 - x);
            case Z ->
                    new Position(direction == Direction.CLOCKWISE ? y : 2 - y, direction == Direction.CLOCKWISE ? 2 - x : x, z);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return x == position.x && y == position.y && z == position.z;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
