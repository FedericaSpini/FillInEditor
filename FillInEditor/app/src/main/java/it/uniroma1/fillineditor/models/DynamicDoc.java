package it.uniroma1.fillineditor.models;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicDoc
{
    private String name;
    private String staticText;
    private ArrayList<DynamicText> fields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.name = name;
    }
}
