package it.uniroma1.fillineditor.models;

import java.util.ArrayList;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDocLibraryModel {
    ArrayList<DynamicDocModel> library;

    public DynamicDocLibraryModel(JSONViewObject[] jsonDocList) {
        this.library = new ArrayList<DynamicDocModel>();
        for (int i = 0; i < jsonDocList.length; i++) {

            JSONViewObject doc = jsonDocList[i];
            library.add(new DynamicDocModel(doc));
        }
    }
    public ArrayList<DynamicDocModel> getLibrary() {return library;}

    public void setLibrary(ArrayList<DynamicDocModel> library) {this.library = library;}
}
