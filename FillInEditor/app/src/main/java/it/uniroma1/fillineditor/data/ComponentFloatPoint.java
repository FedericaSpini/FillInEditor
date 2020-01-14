package it.uniroma1.fillineditor.data;

public class ComponentFloatPoint extends FloatPoint{
    public int component;

    public ComponentFloatPoint( int component, float x, float y) {
        super(x, y);

        this.component = component;
    }
}
