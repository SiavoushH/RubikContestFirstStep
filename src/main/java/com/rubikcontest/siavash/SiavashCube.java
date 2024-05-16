package com.rubikcontest.siavash;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;

import java.util.*;

public class SiavashCube extends RubikCube {

    private Map<Face, Layer> layersByFace;
    private Map<Position, List<Layer>> layersByPosition;

    public SiavashCube(SimpleForm simpleForm) {
        super(simpleForm);
    }

    @Override
    public void rotateFrontClockwise() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Z)
                .face(Face.FRONT)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateFrontCounterClockwise() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Z)
                .face(Face.FRONT)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateBackClockwise() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Z)
                .face(Face.BACK)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateBackCounterClockwise() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Z)
                .face(Face.BACK)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateLeftUpwards() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.X)
                .face(Face.LEFT)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateLeftDownwards() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.X)
                .face(Face.LEFT)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateRightUpwards() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.X)
                .face(Face.RIGHT)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateRightDownwards() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.X)
                .face(Face.RIGHT)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateTopToRight() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Y)
                .face(Face.TOP)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateTopToLeft() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Y)
                .face(Face.TOP)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateBottomToLeft() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Y)
                .face(Face.BOTTOM)
                .direction(Direction.COUNTER_CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    @Override
    public void rotateBottomToRight() {
        RotationInstruction rotationInstruction = RotationInstruction.builder()
                .axis(Axis.Y)
                .face(Face.BOTTOM)
                .direction(Direction.CLOCKWISE)
                .build();
        rotate(rotationInstruction);
    }

    private void rotate(RotationInstruction rotationInstruction) {
        Layer layer = layersByFace.get(rotationInstruction.getFace());
        Set<Cubelet> cubelets = layer.rotate(rotationInstruction);
        for (Map.Entry<Face, Layer> entry : layersByFace.entrySet()) {
            if (!layer.equals(entry.getValue())) {
                entry.getValue().updateWithCubelets(cubelets);
            }
        }
    }

    @Override
    public boolean equals(RubikCube other) {
        return false;
    }

    @Override
    public SimpleForm toSimpleForm() {
        return new SimpleForm(
                layersByFace.get(Face.LEFT).buildFaceArray(),
                layersByFace.get(Face.FRONT).buildFaceArray(),
                layersByFace.get(Face.TOP).buildFaceArray(),
                layersByFace.get(Face.BOTTOM).buildFaceArray(),
                layersByFace.get(Face.RIGHT).buildFaceArray(),
                layersByFace.get(Face.BACK).buildFaceArray()
        );
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {
        layersByFace = new HashMap<>();
        layersByPosition = new HashMap<>();
        prepareLayer(Face.LEFT, simpleForm.getLeft());
        prepareLayer(Face.FRONT, simpleForm.getFront());
        prepareLayer(Face.TOP, simpleForm.getTop());
        prepareLayer(Face.BOTTOM, simpleForm.getBottom());
        prepareLayer(Face.RIGHT, simpleForm.getRight());
        prepareLayer(Face.BACK, simpleForm.getBack());
        createCubelets(simpleForm);
    }

    private void prepareLayer(Face face, char[][] values) {
        Color color = Color.valueOf(values[1][1]);
        Axis axis = calculateAxisByFace(face);
        Layer layer = Layer.Builder.builder()
                .color(color)
                .axis(axis)
                .face(face)
                .build();
        layersByFace.put(face, layer);
        for (Position position : layer.getPositions()) {
            if (!layersByPosition.containsKey(position)) {
                layersByPosition.put(position, new ArrayList<>());
            }
            layersByPosition.get(position).add(layer);
        }
    }

    private void createCubelets(SimpleForm simpleForm) {
        for (Map.Entry<Position, List<Layer>> entry : layersByPosition.entrySet()) {
            Position position = entry.getKey();
            List<Layer> layers = entry.getValue();
            Color front = Color.HIDDEN;
            Color back = Color.HIDDEN;
            Color left = Color.HIDDEN;
            Color right = Color.HIDDEN;
            Color top = Color.HIDDEN;
            Color bottom = Color.HIDDEN;
            for (Layer layer : layers) {
                int[] indexes = layer.mapPositionToIndexes(position);
                switch (layer.getFace()) {
                    case FRONT:
                        front = Color.valueOf(simpleForm.getFront()[indexes[0]][indexes[1]]);
                        break;
                    case BACK:
                        back = Color.valueOf(simpleForm.getBack()[indexes[0]][indexes[1]]);
                        break;
                    case LEFT:
                        left = Color.valueOf(simpleForm.getLeft()[indexes[0]][indexes[1]]);
                        break;
                    case RIGHT:
                        right = Color.valueOf(simpleForm.getRight()[indexes[0]][indexes[1]]);
                        break;
                    case TOP:
                        top = Color.valueOf(simpleForm.getTop()[indexes[0]][indexes[1]]);
                        break;
                    case BOTTOM:
                        bottom = Color.valueOf(simpleForm.getBottom()[indexes[0]][indexes[1]]);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid face: " + layer.getFace());
                }
            }
            Cubelet cubelet = Cubelet.Builder.builder()
                    .front(front)
                    .back(back)
                    .top(top)
                    .bottom(bottom)
                    .left(left)
                    .right(right)
                    .position(position)
                    .build();
            for (Layer layer : layers) {
                layer.addCubelet(cubelet);
            }
        }
    }

    private Axis calculateAxisByFace(Face face) {
        return switch (face) {
            case FRONT, BACK -> Axis.Z;
            case LEFT, RIGHT -> Axis.X;
            case TOP, BOTTOM -> Axis.Y;
        };
    }

    @Override
    public String toString() {
        return "Cube{" +
                "layersByFace=" + layersByFace +
                '}';
    }
}
