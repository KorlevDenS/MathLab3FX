package com.korolevdens.mathlab3fx.integrating;

public enum Methods {

    RIGHT_RECTANGLE_METHOD(2),
    LEFT_RECTANGLE_METHOD(2),
    MIDDLE_RECTANGLE_METHOD(2),
    SIMPSON_METHOD(4),
    TRAPEZOID_METHOD(2);

    private final int accuracyOrder;
    Methods (int accuracyOrder) {
        this.accuracyOrder = accuracyOrder;
    }

    public int getAccuracyOrder() {
        return accuracyOrder;
    }
}
