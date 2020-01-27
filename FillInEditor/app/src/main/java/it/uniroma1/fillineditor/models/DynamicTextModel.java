package it.uniroma1.fillineditor.models;

import java.util.ArrayList;

class DynamicTextModel implements DynamicDocContentModel {
    private int index;
    private int lenght;
    private String description;
    private ArrayList<Character> characterList;

    public DynamicTextModel(int index, int lenght) {
        this.index=index;
        this.lenght=lenght;
        this.description = index+" "+lenght;
    }

    public int getIndex() {return index;}

    public void setIndex(int index) {this.index = index;}

    public int getLenght() {return lenght;}

    public void setLenght(int lenght) {this.lenght = lenght;}

    public ArrayList<Character> getCharacterList() {return characterList;}

    public void setCharacterList(ArrayList<Character> characterList) {this.characterList = characterList;}

    @Override
    public int getViewType() {return 1;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
