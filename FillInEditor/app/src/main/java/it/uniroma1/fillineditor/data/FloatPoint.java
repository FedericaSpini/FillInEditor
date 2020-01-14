package it.uniroma1.fillineditor.data;

public class FloatPoint {
    public  float x, y;

    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
