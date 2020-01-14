package it.uniroma1.fillineditor.data;

public class TimedComponentFloatPoint extends ComponentFloatPoint{
    public long time;

    public TimedComponentFloatPoint(long time, int component, float x, float y) {
        super(component, x, y);
        this.time = time;
    }
}
