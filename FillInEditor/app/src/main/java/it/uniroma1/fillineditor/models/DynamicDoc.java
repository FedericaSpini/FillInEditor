package it.uniroma1.fillineditor.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDoc implements Parcelable {
    private String title;
    private int id;
    private String staticText;
    private ArrayList<ArrayList<Integer>> JSONCompilableText;
    private String encodedCompilableText;

    private DynamicDocContent[] contents;

    public DynamicDoc(JSONViewObject doc){
        this.title = doc.getTitle();
        this.id = doc.getId();
        this.staticText = doc.getText();
        this.JSONCompilableText=doc.getFields();
        this.encodedCompilableText=encodeFields(this.JSONCompilableText);
        setContentsFromJson(doc);
        System.out.println("I CONTENUTI DEL DOCUMENTO "+getTitle()+" SONO: "+contents.toString());
    }

    public String encodeFields(ArrayList<ArrayList<Integer>> json){
        String encodedFields = "";
        for (ArrayList<Integer> indexLength : json) {
            encodedFields += indexLength.get(0) + " " + indexLength.get(1) + "\n";
        }
        System.out.println("||||||||||||||||||||| "+ encodedFields);
        return encodedFields;
    }

    public ArrayList<ArrayList<Integer>> decodeFields(String s){
        ArrayList<ArrayList<Integer>> decodedFields = new ArrayList<ArrayList<Integer>>();
        List<String> rows = Arrays.asList(s.split("\n"));
        for (String r : rows) {
            List<String> values = Arrays.asList(r.split(" "));
            ArrayList<Integer> intList = new ArrayList<Integer>();
            for(String v : values) intList.add(Integer.valueOf(v));
            decodedFields.add(intList);
        }
        System.out.println("||||||||||||||||||||| "+ decodedFields);
        return decodedFields;
    }

    protected DynamicDoc(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.staticText=in.readString();
        this.encodedCompilableText=in.readString();
        this.JSONCompilableText = decodeFields(this.encodedCompilableText);

//        this.contents = (DynamicDocContent[]) in.readArray(getClass().getClassLoader());
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

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeArray(contents);
    }

    public void setContentsFromJson(JSONViewObject doc){
        ArrayList<ArrayList<Integer>>
        ArrayList<DynamicDocContent> c = new ArrayList<DynamicDocContent>();
        String staticText = doc.getText();
        ArrayList<DynamicText> fields = new ArrayList<DynamicText>();
        for (ArrayList<Integer> indexLength : doc.getFields()) {
            fields.add(new DynamicText(indexLength.get(0), indexLength.get(1)));
        }
        if (fields.get(0).getIndex() == 0) {
            c.add(fields.get(0));
            fields.remove(0);
        }
        int index_start = 0;
        for (DynamicText dynT : fields) {
            c.add(new StaticText(staticText.substring(index_start, dynT.getIndex())));
            index_start = dynT.getIndex();
            c.add(dynT);
        }
        if (index_start < staticText.length()) {
            c.add(new StaticText(staticText.substring(index_start)));
        }
        this.contents = c.toArray(new DynamicDocContent[c.size()]);
    }

    public ArrayList<ArrayList<Integer>> readFieldsFromString(){

    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DynamicDoc(String name) {
        this.title = name;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}


    public DynamicDocContent[] getContents() {return contents;}

    public void setContents(DynamicDocContent[] contents) {this.contents = contents;}

    public String getStaticText() {return staticText;}

    public void setStaticText(String staticText) {this.staticText = staticText;}

    public String getJSONCompilableText() {return JSONCompilableText;}

    public void setJSONCompilableText(String JSONCompilableText) {this.JSONCompilableText = JSONCompilableText;}
}
