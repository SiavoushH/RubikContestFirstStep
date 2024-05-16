package com.rubikcontest.mohammad;

import java.util.Map;

class Piece {
    private int x;
    private int y;
    private int z;
    private Map<Axis, RubikColor> colors;

    public Piece(int x, int y, int z, Map<Axis, RubikColor> colors) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.colors = colors;
    }

    public void setColorForAxis(Axis axis, RubikColor color) {
        this.colors.put(axis, color);
    }

    public RubikColor getColorForAxis(Axis axis) {
        return this.colors.get(Axis.getPositiveAxis(axis));
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Piece that)) return false;
        return this.x == ((Piece) obj).getX() && this.y == ((Piece) obj).getY() && this.z == ((Piece) obj).getZ() && this.colors.equals(((Piece) obj).getColors());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Map<Axis, RubikColor> getColors() {
        return this.colors;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        result = 31 * result + this.z;
        return 31 * result + (this.colors == null ? 0 : this.colors.hashCode());
    }

    private void rotateColorAroundZAxis() {
        RubikColor tmpColor = this.colors.get(Axis.X);
        this.colors.put(Axis.X, this.colors.get(Axis.Y));
        this.colors.put(Axis.Y, tmpColor);
    }

    public void rotateClockwizeAroundZAxis() {
        int tmp = this.x;
        this.x = this.y;
        this.y = -1 * tmp;

        this.rotateColorAroundZAxis();
    }

    public void rotateCounterClockwizeAroundZAxis() {
        int tmp = this.y;
        this.y = this.x;
        this.x = -1 * tmp;

        this.rotateColorAroundZAxis();
    }

    private void rotateColorAroundXAxis() {
        RubikColor tmpColor = this.colors.get(Axis.Z);
        this.colors.put(Axis.Z, this.colors.get(Axis.Y));
        this.colors.put(Axis.Y, tmpColor);
    }

    public void rotateClockwizeAroundXAxis() {
        int tmp = this.y;
        this.y = this.z;
        this.z = -1 * tmp;

        this.rotateColorAroundXAxis();
    }

    public void rotateCounterClockwizeAroundXAxis() {
        int tmp = this.z;
        this.z = this.y;
        this.y = -1 * tmp;

        this.rotateColorAroundXAxis();
    }

    private void rotateColorAroundYAxis() {
        RubikColor tmpColor = this.colors.get(Axis.Z);
        this.colors.put(Axis.Z, this.colors.get(Axis.X));
        this.colors.put(Axis.X, tmpColor);
    }

    public void rotateClockwizeAroundYAxis() {
        int tmp = this.z;
        this.z = this.x;
        this.x = -1 * tmp;

        this.rotateColorAroundYAxis();
    }

    public void rotateCounterClockwizeAroundYAxis() {
        int tmp = this.x;
        this.x = this.z;
        this.z = -1 * tmp;

        this.rotateColorAroundYAxis();
    }
}
