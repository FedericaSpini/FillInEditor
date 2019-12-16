package it.uniroma1.fillineditor.configuration;

import android.content.res.Resources;
import it.uniroma1.fillineditor.R;
import it.uniroma1.fillineditor.io.JSONResReader;

public class ConfigurationReader
{
    public static JSONViewObject[] read  (Resources resources){

        JSONResReader reader = new JSONResReader(resources, new R.raw.config);
        JSONViewObject[] configurationJson = reader.constructUsingGson(JSONViewObject[].class);
//        System.out.println(Arrays.toString(configurationJson));
        return configurationJson;
    }
}