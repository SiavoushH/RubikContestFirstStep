package com.rubikcontest.sadeq;

public class Cubelet {
    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public byte getZ() {
        return z;
    }

    private byte x;
    private byte y;
    private byte z;
    private Color xPlaneColor = Color.Unknown;
    private Color yPlaneColor = Color.Unknown;
    private Color zPlaneColor = Color.Unknown;

    public Cubelet(byte x, byte y, byte z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Cubelet rotateAroundXAxis(boolean clockWise) {
        byte oldY = y;
        byte oldZ = z;
        Color oldYPlaneColor = yPlaneColor;
        Color oldZPlaneColor = zPlaneColor;

        y = (byte) (clockWise ? oldZ : -oldZ);
        z = (byte) (clockWise ? -oldY : oldY);
        yPlaneColor = oldZPlaneColor;
        zPlaneColor = oldYPlaneColor;

        return this;
    }

    public Cubelet rotateAroundYAxis(boolean clockWise) {
        byte oldX = x;
        byte oldZ = z;
        Color oldXPlaneColor = xPlaneColor;
        Color oldZPlaneColor = zPlaneColor;

        x = (byte) (clockWise ? -oldZ : oldZ);
        z = (byte) (clockWise ? oldX : -oldX);
        xPlaneColor = oldZPlaneColor;
        zPlaneColor = oldXPlaneColor;

        return this;
    }

    public Cubelet rotateAroundZAxis(boolean clockWise) {
        byte oldX = x;
        byte oldY = y;
        Color oldXPlaneColor = xPlaneColor;
        Color oldYPlaneColor = yPlaneColor;

        x = (byte) (clockWise ? oldY : -oldY);
        y = (byte) (clockWise ? -oldX : oldX);
        xPlaneColor = oldYPlaneColor;
        yPlaneColor = oldXPlaneColor;

        return this;
    }

    public Color getFaceColor(PlaneAxis axis) {
        return switch (axis) {
            case X -> xPlaneColor;
            case Y -> yPlaneColor;
            case Z -> zPlaneColor;
        };
    }

    public Cubelet setFaceColor(PlaneAxis axis, Color color) {
        if (axis.equals(PlaneAxis.X)) {
            xPlaneColor = color;
        } else if (axis.equals(PlaneAxis.Y)) {
            yPlaneColor = color;
        } else if (axis.equals(PlaneAxis.Z)) {
            zPlaneColor = color;
        }

        return this;
    }

    @Override
    public String toString() {
        return "Cubelet{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", xPlaneColor=" + xPlaneColor +
                ", yPlaneColor=" + yPlaneColor +
                ", zPlaneColor=" + zPlaneColor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cubelet cubelet = (Cubelet) o;

        if (x != cubelet.x) return false;
        if (y != cubelet.y) return false;
        return z == cubelet.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = result + 2 * (int) y;
        result = result + 4 * (int) z;
        return result;
    }
}
