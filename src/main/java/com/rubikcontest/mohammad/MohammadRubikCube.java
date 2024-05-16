package com.rubikcontest.mohammad;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;

import java.util.*;
import java.util.stream.Collectors;

public class MohammadRubikCube extends RubikCube {

    List<Piece> pieces;

    Map<FaceName, Face> faces;

    public MohammadRubikCube(SimpleForm simpleForm) {
        super(simpleForm);
    }

    private void applyChange() {
        faces.get(FaceName.FRONT).setPieces(this.pieces.stream().filter(piece -> piece.getZ() == 1).collect(Collectors.toList()));
        faces.get(FaceName.BACK).setPieces(this.pieces.stream().filter(piece -> piece.getZ() == -1).collect(Collectors.toList()));
        faces.get(FaceName.RIGHT).setPieces(this.pieces.stream().filter(piece -> piece.getX() == 1).collect(Collectors.toList()));
        faces.get(FaceName.LEFT).setPieces(this.pieces.stream().filter(piece -> piece.getX() == -1).collect(Collectors.toList()));
        faces.get(FaceName.TOP).setPieces(this.pieces.stream().filter(piece -> piece.getY() == 1).collect(Collectors.toList()));
        faces.get(FaceName.BOTTOM).setPieces(this.pieces.stream().filter(piece -> piece.getY() == -1).collect(Collectors.toList()));
    }

    @Override
    public void rotateFrontClockwise() {
        this.faces.get(FaceName.FRONT).rotateClockwize();
        this.applyChange();
    }

    @Override
    public void rotateFrontCounterClockwise() {
        this.faces.get(FaceName.FRONT).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateBackClockwise() {
        this.faces.get(FaceName.BACK).rotateClockwize();
        this.applyChange();
    }

    @Override
    public void rotateBackCounterClockwise() {
        this.faces.get(FaceName.BACK).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateLeftUpwards() {
        this.faces.get(FaceName.LEFT).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateLeftDownwards() {
        this.faces.get(FaceName.LEFT).rotateClockwize();
        this.applyChange();
    }

    @Override
    public void rotateRightUpwards() {
        this.faces.get(FaceName.RIGHT).rotateClockwize();
        this.applyChange();
    }

    @Override
    public void rotateRightDownwards() {
        this.faces.get(FaceName.RIGHT).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateTopToRight() {
        this.faces.get(FaceName.TOP).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateTopToLeft() {
        this.faces.get(FaceName.TOP).rotateClockwize();
        this.applyChange();
    }

    @Override
    public void rotateBottomToLeft() {
        this.faces.get(FaceName.BOTTOM).rotateCounterClockwize();
        this.applyChange();
    }

    @Override
    public void rotateBottomToRight() {
        this.faces.get(FaceName.BOTTOM).rotateClockwize();
        this.applyChange();
    }

    @Override
    public boolean equals(RubikCube other) {
        if (this == other) return true;
        if (other == null) return false;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MohammadRubikCube that)) return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public SimpleForm toSimpleForm() {
        String sb = this.faces.get(FaceName.LEFT).toString() +
                this.faces.get(FaceName.FRONT).toString() +
                this.faces.get(FaceName.TOP).toString() +
                this.faces.get(FaceName.BOTTOM).toString() +
                this.faces.get(FaceName.RIGHT).toString() +
                this.faces.get(FaceName.BACK).toString();
        return new SimpleForm(sb);
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {
        this.pieces = new ArrayList<>();
        this.faces = new HashMap<FaceName, Face>();

        // filling all with z=1
        for (int i = 1; i >= -1; i--) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                this.pieces.add(new Piece(j, i, 1, new HashMap<Axis, RubikColor>()));
            }
        }

        // filling all with z=-1
        for (int i = 1; i >= -1; i--) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                this.pieces.add(new Piece(j, i, -1, new HashMap<Axis, RubikColor>()));
            }
        }

        // filling all with z=0
        for (int i = 1; i >= -1; i -= 2) {
            for (int j = -1; j <= 1; j += 2) {
                this.pieces.add(new Piece(j, i, 0, new HashMap<Axis, RubikColor>()));
            }
        }

        faces.put(FaceName.FRONT, new Face(
                Axis.Z,
                this.pieces.stream().filter(piece -> piece.getZ() == 1).collect(Collectors.toList()),
                simpleForm.getFront()
        ));

        faces.put(FaceName.BACK, new Face(
                Axis.MZ,
                this.pieces.stream().filter(piece -> piece.getZ() == -1).collect(Collectors.toList()),
                simpleForm.getBack()
        ));

        faces.put(FaceName.LEFT, new Face(
                Axis.MX,
                this.pieces.stream().filter(piece -> piece.getX() == -1).collect(Collectors.toList()),
                simpleForm.getLeft()
        ));

        faces.put(FaceName.RIGHT, new Face(
                Axis.X,
                this.pieces.stream().filter(piece -> piece.getX() == 1).collect(Collectors.toList()),
                simpleForm.getRight()
        ));

        faces.put(FaceName.TOP, new Face(
                Axis.Y,
                this.pieces.stream().filter(piece -> piece.getY() == 1).collect(Collectors.toList()),
                simpleForm.getTop()
        ));

        faces.put(FaceName.BOTTOM, new Face(
                Axis.MY,
                this.pieces.stream().filter(piece -> piece.getY() == -1).collect(Collectors.toList()),
                simpleForm.getBottom()
        ));
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.pieces.toArray());
    }
}
