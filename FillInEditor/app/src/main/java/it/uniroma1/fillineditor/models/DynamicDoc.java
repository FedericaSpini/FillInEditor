package it.uniroma1.fillineditor.models;

import java.util.ArrayList;
import java.util.HashMap;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDoc
{
    private String title;
    private int id;
    private String staticText;
    private ArrayList<DynamicText> fields;

    public DynamicDoc(JSONViewObject doc) {
        this.title = doc.getTitle();
        this.id = doc.getId();
        this.staticText = doc.getText();
        this.fields = new ArrayList<DynamicText>();
        for (ArrayList<Integer> indexLength : doc.getFields()) {
            this.fields.add(new DynamicText(indexLength.get(0), indexLength.get(1)));
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStaticText() {
        return staticText;
    }

    public void setStaticText(String staticText) {
        this.staticText = staticText;
    }

    public HashMap<Integer, DynamicText> getDynamicTextHashMap() {
        return dynamicTextHashMap;
    }

    public void setDynamicTextHashMap(HashMap<Integer, DynamicText> dynamicTextHashMap) {
        this.dynamicTextHashMap = dynamicTextHashMap;
    }

    private HashMap<Integer, DynamicText> dynamicTextHashMap;

    public DynamicDoc(String name) {
        this.title = name;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public ArrayList<DynamicText> getFields() {return fields;}

    public void setFields(ArrayList<DynamicText> fields) {this.fields = fields;}
}
