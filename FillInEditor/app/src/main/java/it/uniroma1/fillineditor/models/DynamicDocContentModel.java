package it.uniroma1.fillineditor.models;

public interface DynamicDocContent {
    public static int STATIC_TEXT_ID = 0;
    public static int DYNAMIC_TEXT_ID = 1;

    public int getViewType();
}
