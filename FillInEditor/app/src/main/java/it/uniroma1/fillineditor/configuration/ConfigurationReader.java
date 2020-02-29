package it.uniroma1.fillineditor.configuration;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;

import it.uniroma1.fillineditor.io.JSONResReader;

public class ConfigurationReader
{
    private Context context;

    public ConfigurationReader(Context context) {
        this.context = context;
    }

    public JSONViewObject[] read  (Resources resources){

        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier("config",
                        "raw", context.getPackageName()));
        JSONResReader reader = new JSONResReader(resources, ins);
        JSONViewObject[] configurationJson = reader.constructUsingGson(JSONViewObject[].class);
        return configurationJson;
    }

}