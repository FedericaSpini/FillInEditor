package it.uniroma1.fillineditor.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.fillineditor.configuration.JSONViewObject;

public class DynamicDocModel implements Parcelable {
    private String title;
    private int id;
    private String staticText;
    private ArrayList<ArrayList<Integer>> JSONCompilableText;
    private String encodedCompilableText;

    private DynamicDocContentModel[] contents;
    private String[] stringContents;

    public DynamicDocModel(JSONViewObject doc){
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

    protected DynamicDocModel(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.staticText=in.readString();
        this.encodedCompilableText=in.readString();
        this.JSONCompilableText = decodeFields();
        setContentsFromJson();
    }

    public static final Creator<DynamicDocModel> CREATOR = new Creator<DynamicDocModel>() {
        @Override
        public DynamicDocModel createFromParcel(Parcel in) {
            return new DynamicDocModel(in);
        }

        @Override
        public DynamicDocModel[] newArray(int size) {
            return new DynamicDocModel[size];
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
        ArrayList<DynamicDocContentModel> c = new ArrayList<DynamicDocContentModel>();
        ArrayList<String> stringC = new ArrayList<String>();

        ArrayList<DynamicTextModel> dynamicFields = new ArrayList<DynamicTextModel>();
        for (ArrayList<Integer> indexLength : this.JSONCompilableText) {
            dynamicFields.add(new DynamicTextModel(indexLength.get(0), indexLength.get(1)));
        }

        if (dynamicFields.get(0).getIndex() == 0) {
            c.add(dynamicFields.get(0));
            stringC.add("2"+dynamicFields.get(0).getDescription());
            dynamicFields.remove(0);
        }
        int index_start = 0;
        for (DynamicTextModel dynT : dynamicFields) {
            StaticTextModel staticText = new StaticTextModel(this.staticText.substring(index_start, dynT.getIndex()));
            c.add(staticText);
            stringC.add("0"+staticText.getText());
            index_start = dynT.getIndex();
            c.add(dynT);
            stringC.add("2"+dynT.getDescription());
        }
        if (index_start < this.staticText.length()) {
            StaticTextModel finalText = new StaticTextModel(this.staticText.substring(index_start));
            c.add(finalText);
            stringC.add("0"+finalText.getText());
        }
        this.contents = c.toArray(new DynamicDocContentModel[c.size()]);
        this.stringContents = stringC.toArray(new String[stringC.size()]);
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public DynamicDocModel(String name) {this.title = name;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public DynamicDocContentModel[] getContents() {return contents;}

    public void setContents(DynamicDocContentModel[] contents) {this.contents = contents;}

    public String getStaticText() {return staticText;}

    public void setStaticText(String staticText) {this.staticText = staticText;}

    public ArrayList<ArrayList<Integer>> getJSONCompilableText() {return JSONCompilableText;}

    public void setJSONCompilableText(ArrayList<ArrayList<Integer>> JSONCompilableText) {this.JSONCompilableText = JSONCompilableText; }

    public String getEncodedCompilableText() {return encodedCompilableText;}

    public void setEncodedCompilableText(String encodedCompilableText) {this.encodedCompilableText = encodedCompilableText;}

    public String[] getStringContents() {return stringContents;}

    public void setStringContents(String[] stringContents) {this.stringContents = stringContents;}

}
