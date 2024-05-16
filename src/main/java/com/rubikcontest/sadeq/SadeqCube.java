package com.rubikcontest.sadeq;

import com.rubikcontest.RubikCube;
import com.rubikcontest.SimpleForm;

import java.util.HashSet;

public class SadeqCube extends RubikCube {
    private HashSet<Cubelet> cubelets;
    private Layer frontLayer;
    private Layer backLayer;
    private Layer topLayer;
    private Layer bottomLayer;
    private Layer rightLayer;
    private Layer leftLayer;

    public SadeqCube(SimpleForm simpleForm) {
        super(simpleForm);
    }

    @Override
    public void rotateFrontClockwise() {
        frontLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateFrontCounterClockwise() {
        frontLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public void rotateBackClockwise() {
        backLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateBackCounterClockwise() {
        backLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public void rotateLeftUpwards() {
        leftLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateLeftDownwards() {
        leftLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public void rotateRightUpwards() {
        rightLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateRightDownwards() {
        rightLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public void rotateTopToRight() {
        topLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public void rotateTopToLeft() {
        topLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateBottomToLeft() {
        bottomLayer.rotate(true);
        recalcLayers();
    }

    @Override
    public void rotateBottomToRight() {
        bottomLayer.rotate(false);
        recalcLayers();
    }

    @Override
    public boolean equals(RubikCube other) {
        return false;
    }

    @Override
    public SimpleForm toSimpleForm() {
        return new SimpleForm(
                leftLayer.toSimpleForm(),
                frontLayer.toSimpleForm(),
                topLayer.toSimpleForm(),
                bottomLayer.toSimpleForm(),
                rightLayer.toSimpleForm(),
                backLayer.toSimpleForm()
        );
    }

    private void recalcLayers() {
        frontLayer = Layer.frontLayer();
        recalcLayer(frontLayer);

        backLayer = Layer.backLayer();
        recalcLayer(backLayer);

        leftLayer = Layer.leftLayer();
        recalcLayer(leftLayer);

        rightLayer = Layer.rightLayer();
        recalcLayer(rightLayer);

        topLayer = Layer.topLayer();
        recalcLayer(topLayer);

        bottomLayer = Layer.bottomLayer();
        recalcLayer(bottomLayer);
    }

    private void recalcLayer(Layer layer) {
        for (Cubelet cubelet : cubelets) {
            layer.addCubelet(cubelet);
        }
    }

    @Override
    protected void initUsingSimpleForm(SimpleForm simpleForm) {
        cubelets = new HashSet<>();
        frontLayer = Layer.frontLayer();
        updateLayer(this.frontLayer, simpleForm.getFront());

        backLayer = Layer.backLayer();
        updateLayer(this.backLayer, simpleForm.getBack());

        topLayer = Layer.topLayer();
        updateLayer(topLayer, simpleForm.getTop());

        bottomLayer = Layer.bottomLayer();
        updateLayer(bottomLayer, simpleForm.getBottom());

        rightLayer = Layer.rightLayer();
        updateLayer(rightLayer, simpleForm.getRight());

        leftLayer = Layer.leftLayer();
        updateLayer(leftLayer, simpleForm.getLeft());
    }

    private void updateLayer(Layer layer, char[][] simpleForm) {
        for(Cubelet cubelet:cubelets) {
            layer.addCubelet(cubelet);
        }
        layer.updateUsingSimpleForm(simpleForm);
        cubelets.addAll(layer.getCubelets());
    }
}
