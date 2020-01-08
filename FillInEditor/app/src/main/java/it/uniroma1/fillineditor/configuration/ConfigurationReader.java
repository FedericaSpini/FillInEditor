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
        System.out.println("??????????????????????????1");

        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier("config",
                        "raw", context.getPackageName()));
        System.out.println("??????????????????????????2"+ins);

//        InputStream XmlFileInputStream = context.getResources().openRawResource(R.raw.config); // getting XML
//        String sxml = readTextFile(XmlFileInputStream);

        JSONResReader reader = new JSONResReader(resources, ins);
        System.out.println("??????????????????????????3"+reader);

        JSONViewObject[] configurationJson = reader.constructUsingGson(JSONViewObject[].class);
//        System.out.println(Arrays.toString(configurationJson));
        System.out.println("??????????????????????????4"+configurationJson);
        return configurationJson;
    }

}