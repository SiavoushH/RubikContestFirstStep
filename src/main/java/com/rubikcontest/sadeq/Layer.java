package com.rubikcontest.sadeq;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Layer {
    private PlaneAxis axis;

    private AxisDirection axisDirection;
    private HashSet<Cubelet> cubelets = new HashSet<>();

    public Layer(PlaneAxis axis, AxisDirection axisDirection) {
        this.axis = axis;
        this.axisDirection = axisDirection;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "axis=" + axis +
                ", axisDirection=" + axisDirection +
                ", cubelets=" + cubelets +
                '}';
    }

    public PlaneAxis getAxis() {
        return axis;
    }

    public AxisDirection getAxisDirection() {
        return axisDirection;
    }

    public HashSet<Cubelet> getCubelets() {
        return cubelets;
    }

    public void setCubelets(HashSet<Cubelet> cubelets) {
        this.cubelets = cubelets;
    }

    private List<Integer> calcCubeletPositionOnFace(Cubelet cubelet) {
        List<Integer> position = new ArrayList<>();
        if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Outward)) {
            position.add(-cubelet.getY() + 1);
            position.add(cubelet.getX() + 1);
        } else if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Inward)) {
            position.add(-cubelet.getY() + 1);
            position.add(-cubelet.getX() + 1);
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Outward)) {
            position.add(cubelet.getZ() + 1);
            position.add(cubelet.getX() + 1);
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Inward)) {
            position.add(-cubelet.getZ() + 1);
            position.add(cubelet.getX() + 1);
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Outward)) {
            position.add(-cubelet.getY() + 1);
            position.add(-cubelet.getZ() + 1);
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Inward)) {
            position.add(-cubelet.getY() + 1);
            position.add(cubelet.getZ() + 1);
        }

        return position;
    }

    private List<Byte> calcCubeletPositionInSpace(byte idx, byte jdx) {
        List<Byte> position = new ArrayList<>();
        if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Outward)) {
            position.add((byte)(jdx - 1));
            position.add((byte)(-idx + 1));
            position.add((byte)1);
        } else if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Inward)) {
            position.add((byte)(-jdx + 1));
            position.add((byte)(-idx + 1));
            position.add((byte)-1);
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Outward)) {
            position.add((byte)(jdx - 1));
            position.add((byte)1);
            position.add((byte)(idx - 1));
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Inward)) {
            position.add((byte)(jdx - 1));
            position.add((byte)-1);
            position.add((byte)(-idx + 1));
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Outward)) {
            position.add((byte)1);
            position.add((byte)(-idx + 1));
            position.add((byte)(-jdx + 1));
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Inward)) {
            position.add((byte)-1);
            position.add((byte)(-idx + 1));
            position.add((byte)(jdx - 1));
        }

        return position;
    }

    private boolean belongsToMe(Cubelet cubelet) {
        if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Outward)) {
            return cubelet.getZ() == 1;
        } else if (axis.equals(PlaneAxis.Z) && axisDirection.equals(AxisDirection.Inward)) {
            return cubelet.getZ() == -1;
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Outward)) {
            return cubelet.getY() == 1;
        } else if (axis.equals(PlaneAxis.Y) && axisDirection.equals(AxisDirection.Inward)) {
            return cubelet.getY() == -1;
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Outward)) {
            return cubelet.getX() == 1;
        } else if (axis.equals(PlaneAxis.X) && axisDirection.equals(AxisDirection.Inward)) {
            return cubelet.getX() == -1;
        }

        throw new InvalidParameterException("Layer orientation is incorrect");
    }

    public Layer addCubelet(Cubelet cubelet) {
        if (belongsToMe(cubelet)) {
            cubelets.add(cubelet);
        }

        return this;
    }

    public Layer rotate(boolean clockWise) {
        for (Cubelet cubelet : cubelets) {
            switch (axis) {
                case X -> cubelet.rotateAroundXAxis(clockWise);
                case Y -> cubelet.rotateAroundYAxis(clockWise);
                case Z -> cubelet.rotateAroundZAxis(clockWise);
            }
        }

        return this;
    }

    public char[][] toSimpleForm() {
        char[][] simpleForm = new char[3][3];
        for (Cubelet cubelet : cubelets) {
            List<Integer> position = this.calcCubeletPositionOnFace(cubelet);
            simpleForm[position.get(0)][position.get(1)] = cubelet.getFaceColor(axis).getValue();
        }
        return simpleForm;
    }

    public Layer updateUsingSimpleForm(char[][] simpleForm) {
        for (byte idx = 0; idx <= 2; idx++) {
            for(byte jdx = 0; jdx <= 2; jdx++) {
                List<Byte> position = calcCubeletPositionInSpace(idx, jdx);
                Optional<Cubelet> maybeCubelet = cubelets.stream()
                        .filter(cubelet ->
                                cubelet.getX() == position.get(0)
                                && cubelet.getY() == position.get(1)
                                && cubelet.getZ() == position.get(2)
                        ).findAny();
                if (maybeCubelet.isPresent()) {
                    maybeCubelet.get().setFaceColor(axis, Color.fromValue(simpleForm[idx][jdx]));
                } else {
                    Cubelet cubelet = new Cubelet(position.get(0), position.get(1), position.get(2))
                            .setFaceColor(axis, Color.fromValue(simpleForm[idx][jdx]));
                    cubelets.add(cubelet);
                }
            }
        }

        return this;
    }

    public static Layer frontLayer() {
        return new Layer(PlaneAxis.Z, AxisDirection.Outward);
    }

    public static Layer backLayer() {
        return new Layer(PlaneAxis.Z, AxisDirection.Inward);
    }

    public static Layer topLayer() {
        return new Layer(PlaneAxis.Y, AxisDirection.Outward);
    }

    public static Layer bottomLayer() {
        return new Layer(PlaneAxis.Y, AxisDirection.Inward);
    }

    public static Layer rightLayer() {
        return new Layer(PlaneAxis.X, AxisDirection.Outward);
    }

    public static Layer leftLayer() {
        return new Layer(PlaneAxis.X, AxisDirection.Inward);
    }
}
