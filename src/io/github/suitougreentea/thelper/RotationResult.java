package io.github.suitougreentea.thelper;

public class RotationResult {
    private boolean success;
    private int offsetX, offsetY;
    public RotationResult(boolean success, int offsetX, int offsetY) {
	super();
	this.success = success;
	this.offsetX = offsetX;
	this.offsetY = offsetY;
    }
    public boolean isSuccess() {
        return success;
    }
    public int getOffsetX() {
        return offsetX;
    }
    public int getOffsetY() {
        return offsetY;
    }
}
