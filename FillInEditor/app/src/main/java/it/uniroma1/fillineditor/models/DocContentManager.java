package it.uniroma1.fillineditor.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocContentManager {
    private static DocContentManager docContentManagerInstance = null;

    private int componentTypeId;
    private String description;

    private DocContentManager(){
        setComponentTypeId(-1);
        setDescription("");
    }

    public static DocContentManager getInstance(){
        if (docContentManagerInstance == null){
            docContentManagerInstance = new DocContentManager();
        }
        return docContentManagerInstance;
    }

    public DynamicDocContentModel generateDocComponent(int componentTypeId, String description){
        setComponentTypeId(componentTypeId);
        setDescription(description);
        switch (this.componentTypeId){
            case 0:
                return defineStaticText();
            case 1:
                return defineDynamicText();
        }
        throw new RuntimeException("Component number is not valid");
    }

    public StaticTextModel defineStaticText(){
        return new StaticTextModel(this.description);
    }

    public DynamicTextModel defineDynamicText(){
        List<String> values = Arrays.asList(description.split(" "));
        ArrayList<Integer> intList = new ArrayList<Integer>();
        return new DynamicTextModel(intList.get(0), intList.get(1));
    }

    public int getComponentTypeId() {return componentTypeId;}

    public void setComponentTypeId(int componentTypeId) {this.componentTypeId = componentTypeId;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
