package it.uniroma1.fillineditor.configuration;

import java.util.ArrayList;

public class JSONViewObject {
    private int id;
    private String title;
    private String text;
    private ArrayList<ArrayList<Integer>> fields;
//    private String fields;
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    @Override
    public String toString(){
        return "\n\n"+ "Documento: "+title+ " # "+id+"\n"+"Testo: "+text+"\n"+"campi: "+fields+"\n";
    }
    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public ArrayList<ArrayList<Integer>> getFields() {return fields;}

    public void setFields(ArrayList<ArrayList<Integer>> fields) {this.fields = fields;}

//    public String getFields() {return fields;}
//
//    public void setFields(String fields) {this.fields = fields;}
}
