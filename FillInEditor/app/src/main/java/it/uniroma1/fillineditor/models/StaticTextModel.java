package it.uniroma1.fillineditor.models;

public class StaticTextModel implements DynamicDocContentModel {
    private String text;

    public StaticTextModel(){this.text = "";}

    public StaticTextModel(String s){this.text=s;}

    @Override
    public int getViewType() {return 0;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}
}
