package com.magicsmsboat.finanzapp.finanzapp.data;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by rol on 30.07.2015.
 */
public class FinanzamtData implements Serializable
{
    private HashMap<String,String> mData;

    public static final String DisTyp = "DisTyp";
    public static final String DisId = "DisId";
    public static final String DisKz = "DisKz";
    public static final String DisNameLang = "DisNameLang";
    public static final String DisBld = "DisBld";
    public static final String DisPlz = "DisPlz";
    public static final String DisOrt = "DisOrt";
    public static final String DisStrasse = "DisStrasse";
    public static final String DiSTelvw = "DiSTelvw";
    public static final String DisTel = "DisTel";
    public static final String DisFax = "DisFax";
    public static final String DisBlz = "DisBlz";
    public static final String DisBankbez = "DisBankbez";
    public static final String DisGiro = "DisGiro";
    public static final String DisBic = "DisBic";
    public static final String DisIban = "DisIban";
    public static final String DisDVR = "DisDVR";
    public static final String DisOeffnung = "DisOeffnung";
    public static final String DisFotoUrl = "DisFotoUrl";
    public static final String DisLatitude = "DisLatitude";
    public static final String DisLongitude = "DisLongitude";

    public void readJSON(JsonReader reader) throws IOException
    {
        HashMap<String,String> newData = new HashMap<String,String>();

        reader.beginObject();
        while(reader.hasNext())
        {
            if (JsonToken.NAME != reader.peek()) {reader.skipValue(); continue;}
            String name = reader.nextName();
            if (JsonToken.STRING != reader.peek()) {reader.skipValue(); continue;}
            newData.put(name, reader.nextString());
        }

        reader.endObject();
        mData = newData;
    }

    public String getDataItem(String name)
    {
        String val = mData.get(name);
        if (null == val) return "";
        return val;
    }
}
