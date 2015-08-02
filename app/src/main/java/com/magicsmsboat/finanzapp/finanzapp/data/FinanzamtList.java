package com.magicsmsboat.finanzapp.finanzapp.data;

import android.util.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by rol on 30.07.2015.
 */
public class FinanzamtList
{
    private ArrayList<FinanzamtData> mList;

    public FinanzamtData getItem(int idx) {return  mList.get(idx);}
    public int getCount() {return mList.size();}

    public void initJSON(String json) throws IOException
    {
        ArrayList<FinanzamtData> newList = new ArrayList<FinanzamtData>();
        JsonReader jReader = new JsonReader(new StringReader(json));

        try
        {
            jReader.beginArray();

            while (jReader.hasNext())
            {
                FinanzamtData data = new FinanzamtData();
                data.readJSON(jReader);
                newList.add(data);
            }

            jReader.endArray();
        }
        finally
        {
           jReader.close();
        }

        sort(newList);
        mList = newList;

    }

    private void sort(ArrayList<FinanzamtData> newList)
    {
        Collections.sort(newList,
            new Comparator<FinanzamtData>()
            {
                @Override
                public int compare(FinanzamtData data1, FinanzamtData data2)
                {

                    return data1.getDataItem(FinanzamtData.DisPlz).compareTo(data2.getDataItem(FinanzamtData.DisPlz));
                }
            });
    }
}
