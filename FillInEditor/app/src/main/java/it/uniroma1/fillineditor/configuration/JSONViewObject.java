package it.uniroma1.fillineditor.configuration;

import java.util.HashMap;

public class JSONViewObject {
    private int id;
    private String text;
    private HashMap<Integer, Integer> fields;
//    private String type;
//    public HashMap<String, String> values;
//    private JSONViewObject[] nested_views;

    //    public String toString(){return String.format("{%s [%s]}", type, Arrays.toString(nested_views.toArray()));}
    public String toString(){
        return String.format("{%s %s %s}", id, text, fields.toString());
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public HashMap<Integer, Integer> getFields() {return fields;}

    public void setFields(HashMap<Integer, Integer> fields) {this.fields = fields;}

}
