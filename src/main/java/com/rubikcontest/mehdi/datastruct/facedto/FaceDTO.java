package com.rubikcontest.mehdi.datastruct.facedto;

public class FaceDTO {
    protected char[][] faceVal;

    public FaceDTO(char[][] face)
    {
        this.faceVal = face;
    }
    
    public char[] getTopLine(){
        char[] res = { faceVal[0][0], faceVal[0][1], faceVal[0][2] };
        return res;
    }

    public char[] getBottomLine()
    {
        char[] res = { faceVal[2][0], faceVal[2][1], faceVal[2][2] };
        return res;
    }

    public char[] getRightLine(){
        char[] res = { faceVal[0][2], faceVal[1][2], faceVal[2][2] };
        return res;
    }

    public char[] getLeftLine()
    {
        char[] res = { faceVal[0][0], faceVal[1][0], faceVal[2][0] };
        return res;
    }

    public char[][] getVal()
    {
        return this.faceVal;
    }
}
