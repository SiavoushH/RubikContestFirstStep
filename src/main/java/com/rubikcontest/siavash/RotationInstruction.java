package com.rubikcontest.siavash;

public class RotationInstruction {
    private final Axis axis;
    private final Face face;
    private final Direction direction;

    private RotationInstruction(Builder builder) {
        axis = builder.axis;
        face = builder.face;
        direction = builder.direction;

        validate();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Axis getAxis() {
        return axis;
    }

    public Face getFace() {
        return face;
    }

    public Direction getDirection() {
        return direction;
    }

    private void validate() {
        if (axis == null || face == null || direction == null) {
            throw new IllegalArgumentException("Axis, face and direction must be set");
        }
        if ((face == Face.FRONT || face == Face.BACK) && axis != Axis.Z) {
            throw new IllegalArgumentException("Front and back faces can only be rotated around the Z axis");
        }
        if ((face == Face.LEFT || face == Face.RIGHT) && axis != Axis.X) {
            throw new IllegalArgumentException("Left and right faces can only be rotated around the X axis");
        }
        if ((face == Face.TOP || face == Face.BOTTOM) && axis != Axis.Y) {
            throw new IllegalArgumentException("Top and bottom faces can only be rotated around the Y axis");
        }
    }

    public static final class Builder {
        private Axis axis;
        private Face face;
        private Direction direction;

        private Builder() {
        }

        public Builder axis(Axis val) {
            axis = val;
            return this;
        }

        public Builder face(Face val) {
            face = val;
            return this;
        }

        public Builder direction(Direction val) {
            direction = val;
            return this;
        }

        public RotationInstruction build() {
            return new RotationInstruction(this);
        }
    }
}
