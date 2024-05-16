package com.rubikcontest.mehdi.datastruct;

import com.rubikcontest.SimpleForm;
import com.rubikcontest.mehdi.datastruct.facedto.FaceDTO;

public interface RubikDataStruct {

    public void feed(SimpleForm form);

    // front side getter
    public FaceDTO getFront();


    // back side getter
    public FaceDTO getBack();

    // top side getter
    public FaceDTO getTop();


    // right side getter
    public FaceDTO getRight();


    // left side getter
    public FaceDTO getLeft();


    // bottom side getter
    public FaceDTO getBottom();

    // front side setter
    public void setFrontSide(char[][] front);

    // back side setter
    public void setBackSide(char[][] back);

    // right side setter
    public void setRightSide(char[][] right);

    // left side setter
    public void setLeftSide(char[][] left);

    // bottom side setter
    public void setBottomSide(char[][] bottom);

    // top side setter
    public void setTopSide(char[][] top);

    public SimpleForm toSimpleForm();

    public void flush();

}