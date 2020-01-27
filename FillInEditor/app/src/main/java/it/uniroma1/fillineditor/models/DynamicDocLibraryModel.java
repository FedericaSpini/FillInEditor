package it.uniroma1.fillineditor.models;

import java.util.ArrayList;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDocLibraryModel {
    ArrayList<DynamicDocModel> library;

    public DynamicDocLibraryModel(JSONViewObject[] jsonDocList) {
        System.out.println("CREAZIONE DELLA LIBRERIA DI DOCUMENTI IN CORSO...");
        this.library = new ArrayList<DynamicDocModel>();
        for (int i = 0; i < jsonDocList.length; i++) {

            // accessing each element of array
            JSONViewObject doc = jsonDocList[i];
            library.add(new DynamicDocModel(doc));
        }
        System.out.println("LA LIBRERIA DEI DOCUMENTI E' STATA CREATA");
    }
    public ArrayList<DynamicDocModel> getLibrary() {return library;}

    public void setLibrary(ArrayList<DynamicDocModel> library) {this.library = library;}
}
