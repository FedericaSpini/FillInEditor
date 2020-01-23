package it.uniroma1.fillineditor.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDoc implements Parcelable {
    private String title;
    private int id;
    private String staticText;
    private ArrayList<ArrayList<Integer>> JSONCompilableText;
    private String encodedCompilableText;

    private DynamicDocContent[] contents;
    private String[] stringContents;

    public DynamicDoc(JSONViewObject doc){
        this.title = doc.getTitle();
        this.id = doc.getId();
        this.staticText = doc.getText();
        this.JSONCompilableText=doc.getFields();
        this.encodedCompilableText=encodeFields();
        setContentsFromJson();
    }

    public String encodeFields(){
        String encodedFields = "";
        for (ArrayList<Integer> indexLength : this.JSONCompilableText) {
            encodedFields += indexLength.get(0) + " " + indexLength.get(1) + "\n";
        }
        return encodedFields;
    }

    public ArrayList<ArrayList<Integer>> decodeFields(){
        ArrayList<ArrayList<Integer>> decodedFields = new ArrayList<ArrayList<Integer>>();
        List<String> rows = Arrays.asList(this.encodedCompilableText.split("\n"));
        for (String r : rows) {
            List<String> values = Arrays.asList(r.split(" "));
            ArrayList<Integer> intList = new ArrayList<Integer>();
            for(String v : values) intList.add(Integer.valueOf(v));
            decodedFields.add(intList);
        }
        return decodedFields;
    }

    protected DynamicDoc(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.staticText=in.readString();
        this.encodedCompilableText=in.readString();
        this.JSONCompilableText = decodeFields();
        setContentsFromJson();


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
        dest.writeString(staticText);
        dest.writeString(encodedCompilableText);
    }

    public void setContentsFromJson(){
//        ArrayList<ArrayList<Integer>>
        ArrayList<DynamicDocContent> c = new ArrayList<DynamicDocContent>();
        ArrayList<String> stringC = new ArrayList<String>();

        ArrayList<DynamicText> dynamicFields = new ArrayList<DynamicText>();
        for (ArrayList<Integer> indexLength : this.JSONCompilableText) {
            dynamicFields.add(new DynamicText(indexLength.get(0), indexLength.get(1)));
        }

        if (dynamicFields.get(0).getIndex() == 0) {
            c.add(dynamicFields.get(0));
            stringC.add("1"+dynamicFields.get(0).getDescription());
            dynamicFields.remove(0);
        }
        int index_start = 0;
        for (DynamicText dynT : dynamicFields) {
            StaticText staticText = new StaticText(this.staticText.substring(index_start, dynT.getIndex()));
            c.add(staticText);
            stringC.add("0"+staticText.getText());
            index_start = dynT.getIndex();
            c.add(dynT);
            stringC.add("1"+dynT.getDescription());
        }
        if (index_start < this.staticText.length()) {
            StaticText finalText = new StaticText(this.staticText.substring(index_start));
            c.add(finalText);
            stringC.add("0"+finalText.getText());
        }
        this.contents = c.toArray(new DynamicDocContent[c.size()]);
        this.stringContents = stringC.toArray(new String[stringC.size()]);
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public DynamicDoc(String name) {this.title = name;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public DynamicDocContent[] getContents() {return contents;}

    public void setContents(DynamicDocContent[] contents) {this.contents = contents;}

    public String getStaticText() {return staticText;}

    public void setStaticText(String staticText) {this.staticText = staticText;}

    public ArrayList<ArrayList<Integer>> getJSONCompilableText() {return JSONCompilableText;}

    public void setJSONCompilableText(ArrayList<ArrayList<Integer>> JSONCompilableText) {this.JSONCompilableText = JSONCompilableText; }

    public String getEncodedCompilableText() {return encodedCompilableText;}

    public void setEncodedCompilableText(String encodedCompilableText) {this.encodedCompilableText = encodedCompilableText;}

    public String[] getStringContents() {return stringContents;}

    public void setStringContents(String[] stringContents) {this.stringContents = stringContents;}

}
