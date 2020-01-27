package it.uniroma1.fillineditor.viewComponents;

public interface DynamicDocViewComponents {
    public static int STATIC_TEXT_ID = 0;
    public static int DYNAMIC_TEXT_ID = 1;
    public static int STATIC_BOXES_WORD_ID = 2;
    public int getViewType();
    public void setContents(String s);

}
