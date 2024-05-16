package com.rubikcontest.mehdi.datastruct;

import com.rubikcontest.SimpleForm;
import com.rubikcontest.mehdi.datastruct.facedto.FaceDTO;

public class RubikArrayDataStruct implements RubikDataStruct {

    char[][] left;
    char[][] front;
    char[][] top;
    char[][] bottom;
    char[][] right;
    char[][] back;

    char[][] tempLeft;
    char[][] tempFront;
    char[][] tempTop;
    char[][] tempBottom;
    char[][] tempRight;
    char[][] tempBack;

    @Override
    public void feed(SimpleForm form) {
        this.left = form.getLeft();
        this.right = form.getRight();
        this.top = form.getTop();
        this.bottom = form.getBottom();
        this.back = form.getBack();
        this.front = form.getFront();

        this.tempLeft = form.getLeft();
        this.tempRight = form.getRight();
        this.tempTop = form.getTop();
        this.tempBottom = form.getBottom();
        this.tempBack = form.getBack();
        this.tempFront = form.getFront();

    }

    @Override
    public void setFrontSide(char[][] front) {
        this.tempFront = front;
    }

    @Override
    public void setBackSide(char[][] back) {
        this.tempBack = back;
    }

    @Override
    public void setRightSide(char[][] right) {
        this.tempRight = right;
    }

    @Override
    public void setLeftSide(char[][] left) {
        this.tempLeft = left;
    }

    @Override
    public void setBottomSide(char[][] bottom) {
        this.tempBottom = bottom;
    }

    @Override
    public void setTopSide(char[][] top) {
        this.tempTop = top;
    }

    @Override
    public SimpleForm toSimpleForm() {
        return new SimpleForm(this.left, this.front, this.top, this.bottom, this.right, this.back);
    }

    @Override
    public FaceDTO getFront() {
        FaceDTO dto = new FaceDTO(this.front);
        return dto;
    }

    @Override
    public FaceDTO getBack() {
        FaceDTO dto = new FaceDTO(this.back);
        return dto;
    }

    @Override
    public FaceDTO getTop() {
        FaceDTO dto = new FaceDTO(this.top);
        return dto;
    }

    @Override
    public FaceDTO getRight() {
        FaceDTO dto = new FaceDTO(this.right);
        return dto;
    }

    @Override
    public FaceDTO getLeft() {
        FaceDTO dto = new FaceDTO(this.left);
        return dto;
    }

    @Override
    public FaceDTO getBottom() {
        FaceDTO dto = new FaceDTO(this.bottom);
        return dto;
    }

    @Override
    public void flush() {
        this.back = this.tempBack;
        this.front = this.tempFront;
        this.left = this.tempLeft;
        this.right = this.tempRight;
        this.top = this.tempTop;
        this.bottom = this.tempBottom;
    }
}
