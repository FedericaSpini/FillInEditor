package it.uniroma1.fillineditor.models;

import java.util.ArrayList;

class DynamicText implements DynamicDocContent{
    private int index;
    private int lenght;
    private ArrayList<Character> characterList;

    public DynamicText(int index, int lenght) {
        this.index=index;
        this.lenght=lenght;
    }

    public int getIndex() {return index;}

    public void setIndex(int index) {this.index = index;}

    public int getLenght() {return lenght;}

    public void setLenght(int lenght) {this.lenght = lenght;}

    public ArrayList<Character> getCharacterList() {return characterList;}

    public void setCharacterList(ArrayList<Character> characterList) {this.characterList = characterList;}

    @Override
    public int getViewType() {return 1;}
}
