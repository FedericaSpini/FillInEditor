package it.uniroma1.fillineditor.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDoc implements Parcelable {
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

    protected DynamicDoc(Parcel in) {
        title = in.readString();
        id = in.readInt();
        staticText = in.readString();
    }

    public static final Creator<DynamicDoc> CREATOR = new Creator<DynamicDoc>() {
        @Override
        public DynamicDoc createFromParcel(Parcel in) {
            return new DynamicDoc(in);
        }

        @Override
        public DynamicDoc[] newArray(int size) {
            return new DynamicDoc[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeString(staticText);
    }
}
