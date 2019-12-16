package it.uniroma1.fillineditor.configuration;

import java.util.Arrays;
import java.util.HashMap;

public class JSONViewObject {
    private String type;
    public HashMap<String, String> values;
    private JSONViewObject[] nested_views;

    //    public String toString(){return String.format("{%s [%s]}", type, Arrays.toString(nested_views.toArray()));}
    public String toString(){
        return String.format("{%s %s}", type, Arrays.toString(nested_views));
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public JSONViewObject[] getNested_views() {
        return nested_views;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public void setNested_views(JSONViewObject[] nested_views) {
        this.nested_views = nested_views;
    }

}
