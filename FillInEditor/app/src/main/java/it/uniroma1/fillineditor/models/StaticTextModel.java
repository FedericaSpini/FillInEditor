package it.uniroma1.fillineditor.models;

public class StaticText implements DynamicDocContent {
    private String text;

    public StaticText(){this.text = "";}
    public StaticText(String s){this.text=s;}
    @Override
    public int getViewType() {return 0;}
    public String getText() {return text;}

    public void setText(String text) {this.text = text;}
}
