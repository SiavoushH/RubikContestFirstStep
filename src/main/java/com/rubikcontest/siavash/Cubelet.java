package com.rubikcontest.siavash;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Cubelet {

    private final UUID id;

    private Position position;

    private Color front;
    private Color back;
    private Color left;
    private Color right;
    private Color top;
    private Color bottom;

    private Cubelet(Builder builder) {
        id = Objects.requireNonNullElseGet(builder.id, UUID::randomUUID);
        position = builder.position;
        front = builder.front;
        back = builder.back;
        left = builder.left;
        right = builder.right;
        top = builder.top;
        bottom = builder.bottom;

        validate();
    }

    /**
     * Rotate the cubelet according to the given rotation
     * instruction and put it in its new position
     *
     * @param rotationInstruction The rotation instruction
     */
    public void rotate(RotationInstruction rotationInstruction) {
        updateColors(rotationInstruction);
        updatePosition(rotationInstruction);
    }

    public Position getPosition() {
        return position;
    }

    public Color getColorByFace(Face face) {
        return switch (face) {
            case FRONT -> front;
            case BACK -> back;
            case TOP -> top;
            case BOTTOM -> bottom;
            case LEFT -> left;
            case RIGHT -> right;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cubelet cubelet)) return false;
        return Objects.equals(id, cubelet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     *
     * Orbiting the cubelet around the given axis without rotating it
     *
     * @param rotationInstruction The rotation instruction
     */
    private void updatePosition(RotationInstruction rotationInstruction) {
        position = position.rotate(rotationInstruction.getAxis(), rotationInstruction.getDirection());
    }

    /**
     * Mimics the rotation of the cubelet by reassigning the colors
     *
     * @param rotationInstruction The rotation instruction
     */
    private void updateColors(RotationInstruction rotationInstruction) {
        Map<Face, Color> currentColors = Map.of(
                Face.FRONT, front,
                Face.BACK, back,
                Face.LEFT, left,
                Face.RIGHT, right,
                Face.TOP, top,
                Face.BOTTOM, bottom
        );
        Map<Face, Consumer<Color>> faceColorSetter = Map.of(
                Face.FRONT, color -> front = color,
                Face.BACK, color -> back = color,
                Face.LEFT, color -> left = color,
                Face.RIGHT, color -> right = color,
                Face.TOP, color -> top = color,
                Face.BOTTOM, color -> bottom = color
        );

        for (Face face : Face.asList()) {
            Face rotatedFace = rotateFace(face, rotationInstruction);
            faceColorSetter.get(rotatedFace).accept(currentColors.get(face));
        }
    }

    /**
     * Find the face that we want to apply the color of the current face to
     *
     * @param face The current face
     * @param rotationInstruction The rotation instruction
     * @return The face that we want to apply the color of the current face to
     */
    private Face rotateFace(Face face, RotationInstruction rotationInstruction) {
        Axis axis = rotationInstruction.getAxis();
        boolean isClockwise = rotationInstruction.getDirection() == Direction.CLOCKWISE;
        if (axis == Axis.Z) {
            return mapFace(face, isClockwise, Map.of(Face.LEFT, Face.TOP, Face.RIGHT, Face.BOTTOM,
                    Face.TOP, Face.RIGHT, Face.BOTTOM, Face.LEFT));
        } else if (axis == Axis.X) {
            return mapFace(face, isClockwise, Map.of(Face.FRONT, Face.BOTTOM, Face.BACK, Face.TOP,
                    Face.TOP, Face.FRONT, Face.BOTTOM, Face.BACK));
        } else if (axis == Axis.Y) {
            return mapFace(face, isClockwise, Map.of(Face.FRONT, Face.RIGHT, Face.BACK, Face.LEFT,
                    Face.LEFT, Face.FRONT, Face.RIGHT, Face.BACK));
        }
        return face;
    }

    private Face mapFace(Face face, boolean isClockwise, Map<Face, Face> mappings) {
        Map<Face, Face> flippedMappings = mappings.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getValue,
                                Map.Entry::getKey
                        )
                );
        return isClockwise ? mappings.getOrDefault(face, face) : flippedMappings.getOrDefault(face, face);
    }

    private void validate() {
        validateAllAxesPositions();
        validateCubeletIsAtSurface();
        validateVisibleColors();
    }

    private void validateVisibleColors() {
        validateColorVisibility(position.x(), left, right, "Left", "Right");
        validateColorVisibility(position.y(), bottom, top, "Bottom", "Top");
        validateColorVisibility(position.z(), front, back, "Front", "Back");
    }

    // if we stand at (0, 0, 0) and look at the cube, the near face is the face that is closest to us
    private void validateColorVisibility(
            int axisPosition,
            Color nearFaceColor,
            Color farFaceColor,
            String nearFaceColorName,
            String farFaceColorName
    ) {
        if (nearFaceColor == null || farFaceColor == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        if (axisPosition == 0) {
            if (nearFaceColor == Color.HIDDEN) {
                throw new IllegalArgumentException(nearFaceColorName + " color must be visible");
            } else if (farFaceColor != Color.HIDDEN) {
                throw new IllegalArgumentException(farFaceColorName + " color must be hidden");
            }
        } else if (axisPosition == 2) {
            if (farFaceColor == Color.HIDDEN) {
                throw new IllegalArgumentException(farFaceColorName + " color must be visible");
            } else if (nearFaceColor != Color.HIDDEN) {
                throw new IllegalArgumentException(nearFaceColorName + " color must be hidden");
            }
        }
    }

    private void validateAllAxesPositions() {
        validateAxisPosition(position.x());
        validateAxisPosition(position.y());
        validateAxisPosition(position.z());
    }

    private void validateAxisPosition(int axisPosition) {
        if (axisPosition < 0 || axisPosition > 2) {
            throw new IllegalArgumentException("Axis position must be between 0 and 2");
        }
    }

    private void validateCubeletIsAtSurface() {
        if ((position.x() % 2) * (position.y() % 2) * (position.z() % 2) != 0) {
            throw new IllegalArgumentException("Cubelet must be at the surface of the cube");
        }
    }

    public static final class Builder {
        private UUID id;
        private Position position;
        private Color front = Color.HIDDEN;
        private Color back = Color.HIDDEN;
        private Color left = Color.HIDDEN;
        private Color right = Color.HIDDEN;
        private Color top = Color.HIDDEN;
        private Color bottom = Color.HIDDEN;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder position(Position val) {
            position = val;
            return this;
        }

        public Builder front(Color val) {
            front = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Builder back(Color val) {
            back = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Builder left(Color val) {
            left = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Builder right(Color val) {
            right = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Builder top(Color val) {
            top = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Builder bottom(Color val) {
            bottom = val == null ? Color.HIDDEN : val;
            return this;
        }

        public Cubelet build() {
            return new Cubelet(this);
        }
    }

    @Override
    public String toString() {
        return "Cubelet{" +
                "\nid=" + id +
                "\n, position=" + position +
                "\n, front=" + front +
                "\n, back=" + back +
                "\n, left=" + left +
                "\n, right=" + right +
                "\n, top=" + top +
                "\n, bottom=" + bottom + "\n" +
                '}';
    }
}
