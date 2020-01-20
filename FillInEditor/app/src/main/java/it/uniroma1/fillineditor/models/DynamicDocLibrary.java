package it.uniroma1.fillineditor.models;

import java.util.ArrayList;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDocLibrary {
    ArrayList<DynamicDoc> library;

    public DynamicDocLibrary(JSONViewObject[] jsonDocList) {
        System.out.println("CREAZIONE DELLA LIBRERIA DI DOCUMENTI IN CORSO...");
        this.library = new ArrayList<DynamicDoc>();
        for (int i = 0; i < jsonDocList.length; i++) {

            // accessing each element of array
            JSONViewObject doc = jsonDocList[i];
            library.add(new DynamicDoc(doc));
        }
        System.out.println("LA LIBRERIA DEI DOCUMENTI E' STATA CREATA");
    }
    public ArrayList<DynamicDoc> getLibrary() {return library;}

    public void setLibrary(ArrayList<DynamicDoc> library) {this.library = library;}
}
